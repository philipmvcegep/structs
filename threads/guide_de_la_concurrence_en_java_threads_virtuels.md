# Concurrence moderne en Java : Guide des threads virtuels et mécanismes clés

---

## 1. Threads porteurs (Carrier Threads) et l'architecture M:N

Les threads virtuels ne s'exécutent pas directement sur les cœurs physiques du processeur (CPU). Ils sont plutôt gérés par la machine virtuelle Java (JVM) à l'aide d'un **modèle d'ordonnancement M:N**.

* **Threads virtuels (Tâches en espace utilisateur) :** Des millions de tâches extrêmement légères vivant dans le tas (Heap) de la JVM.
* **Threads porteurs / Carrier Threads (Moteurs d'exécution de l'OS) :** Un petit groupe de threads de plate-forme lourds (gérés par un `ForkJoinPool` interne) qui exécutent concrètement les threads virtuels sur le matériel.

Lorsqu'un thread virtuel rencontre une opération d'E/S (I/O) bloquante, la JVM le **démonte** (unmount) de son thread porteur en laissant l'état de sa pile sur le tas. Le thread porteur est immédiatement libéré pour prendre en charge un autre thread virtuel.

### Exemple de code : Inspecter les threads porteurs et virtuels

```java
public class CarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread vThread = Thread.ofVirtual().start(() -> {
            Thread current = Thread.currentThread();
            System.out.println("Nom du thread courant : " + current.getName());
            System.out.println("Est-ce un thread virtuel ? " + current.isVirtual());
        });
        
        vThread.join();
    }
}
```

---

## 2. Conditions de course (Race Conditions) et le mot-clé `synchronized`

Une **condition de course** survient lorsque plusieurs threads accèdent simultanément à une donnée mutable partagée, et qu'au moins l'un d'entre eux la modifie. Comme l'ordonnancement des threads n'est pas prédictible, des opérations comme `count++` (qui se composent en réalité de trois étapes au niveau machine : lire, incrémenter, écrire) peuvent s'entrelacer et écraser des données.

Pour résoudre cela, le mot-clé traditionnel **`synchronized`** crée une **section critique** à l'aide d'un verrou (monitor), garantissant qu'un seul thread à la fois peut exécuter ce bloc de code.

> ⚠️ **Le piège avec les threads virtuels (Pinning) :** Si un thread virtuel se bloque à l'intérieur d'un bloc `synchronized` (ex. lors d'un `Thread.sleep()` ou en attendant un verrou hérité), il provoque un **ancrage (thread pinning)**. Le thread porteur sous-jacent se retrouve captif et bloqué.

### Exemple de code : Corriger une condition de course avec `synchronized`

```java
public class CounterDemo {
    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        // synchronized garantit l'exclusion mutuelle (1 seul thread à la fois)
        synchronized (lock) {
            count++; 
        }
    }

    public int getCount() {
        return count;
    }
}
```

---

## 3. Sécurité sans verrou avec les classes atomiques (`java.util.concurrent.atomic`)

Lorsque vous avez besoin de sécuriser l'accès à une seule variable sans subir le surcoût de performance ou les risques d'ancrage de `synchronized`, Java propose des **classes atomiques** (comme `AtomicInteger`, `AtomicLong`).

Au lieu de verrouiller l'accès aux autres threads, les classes atomiques utilisent des instructions processeur bas niveau appelées **CAS (Compare-And-Swap)**. Elles tentent la mise à jour de manière optimiste ; si un autre thread a modifié la variable entre-temps, la variable atomique réessaye automatiquement jusqu'à réussir.

### Exemple de code : Compteur sans verrou avec `AtomicInteger`

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    private final AtomicInteger safeCount = new AtomicInteger(0);

    public void increment() {
        // Incrémente de manière atomique sans nécessiter de bloc synchronized
        safeCount.incrementAndGet();
    }

    public int getCount() {
        return safeCount.get();
    }
}
```

---

## 4. Les trois usages du mot-clé `final`

Le mot-clé `final` signifie "ne peut pas être modifié", mais son comportement varie selon l'élément auquel il est appliqué :

1. **Variable `final` :** Ne peut pas être réaffectée une fois initialisée. Essentiel en concurrence pour la **visibilité mémoire**. Requis lors de la capture de variables locales dans des lambdas ou des threads (doit être `final` ou "effectivement final").
2. **Méthode `final` :** Ne peut pas être redéfinie (override) dans une classe fille. Utiliser pour sécuriser la logique d'affaires ou des règles de conception.
3. **Classe `final` :** Ne peut pas être dérivée/héritée (ex. `String`). Empêche toute sous-classe.

### Exemple de code : Variables effectivement finales dans les threads

```java
public class FinalCaptureDemo {
    public static void main(String[] args) throws InterruptedException {
        // Variable "effectivement finale" car jamais réaffectée
        final String prefix = "[LOG]"; 

        Thread vThread = Thread.ofVirtual().start(() -> {
            // La lambda dans le thread virtuel peut lire la variable finale capturée
            System.out.println(prefix + " Tâche exécutée avec succès.");
        });

        vThread.join();
    }
}
```

---

## 5. Résultats asynchrones avec `Callable` et `Future`

Alors qu'une tâche `Runnable` s'exécute sans rien retourner, **`Callable<T>`** permet à une tâche en arrière-plan de **retourner une valeur** et d'émettre des exceptions contrôlées.

Lorsque vous soumettez un `Callable` à un exécuteur, il retourne un **`Future<T>`**, qui agit comme un conteneur/handle pour le résultat à venir. L'appel à `future.get()` bloque le thread appelant jusqu'à ce que le thread virtuel ait fini son calcul.

### Exemple de code : Récupérer des résultats avec `Future`

```java
import java.util.concurrent.*;

public class CallableFutureDemo {
    public static void main(String[] args) throws Exception {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            // Soumission d'une tâche Callable qui retourne un String
            Future<String> futureResult = executor.submit(() -> {
                Thread.sleep(100); // Simulation d'un délai réseau ou BD
                return "Données récupérées avec succès du serveur !";
            });

            System.out.println("Exécution d'autres tâches pendant le calcul...");
            
            // future.get() bloque jusqu'à ce que le thread virtuel termine et donne son résultat
            String result = futureResult.get();
            System.out.println(result);
        }
    }
}
```

---

## 6. Gestion propre du cycle de vie avec Try-With-Resources et Exécuteurs

La façon recommandée d'exécuter des threads virtuels à grande échelle est d'utiliser **`Executors.newVirtualThreadPerTaskExecutor()`** dans un bloc **try-with-resources**.

Ce modèle agit comme une **barrière de synchronisation implicite**. Le bloc try-with-resources garantit que le programme **attendra automatiquement** que tous les threads virtuels soumis soient terminés avant d'exécuter la suite, évitant ainsi toute fuite de threads.

### Exemple de code : Exécution structurée avec Try-With-Resources

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ExecutorTryWithResourcesDemo {
    public static void main(String[] args) {
        // Le bloc try-with-resources ferme et attend automatiquement toutes les tâches
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            IntStream.range(1, 6).forEach(i -> {
                executor.submit(() -> {
                    System.out.println("Traitement de la tâche " + i + " sur " + Thread.currentThread().getName());
                });
            });
            
        } // <- Le programme attend ici que les 5 threads virtuels soient complètement terminés !
        
        System.out.println("Tous les threads virtuels sont terminés. Fin du programme.");
    }
}
```
