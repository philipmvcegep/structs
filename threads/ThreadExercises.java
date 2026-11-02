package threads;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadExercises {

    public static void main(String[] args) throws Exception {

        // Décommenter un exercice pour le tester.

        // exercise1();
        // exercise2();
        // exercise3();
        // exercise4();
        // exercise5();
        // exercise6();
        // exercise7();
        // exercise8();
        // exercise9();
        // exercise10();
    }

    // =====================================================
    // EXERCICE 1 : HELLO THREAD
    // Difficulté : Facile
    // =====================================================

    // Objectif :
    // Créer un Runnable qui affiche un message.
    // Exécuter ce Runnable dans un nouveau thread.

    public static void exercise1() {

        // TODO 1 : Créer un Runnable avec une lambda.
        Runnable task = null;

        // TODO 2 : Créer un nouveau Thread avec ce Runnable.


        // TODO 3 : Démarrer le thread.


    }


    // =====================================================
    // EXERCICE 2 : DEUX TÂCHES SIMULTANÉES
    // Difficulté : Facile
    // =====================================================

    // Objectif :
    // Créer deux threads qui exécutent des tâches différentes.
    // Attendre que les deux threads terminent.

    public static void exercise2() throws InterruptedException {

        Runnable task1 = () ->
            System.out.println("Téléchargement du fichier A...");

        Runnable task2 = () ->
            System.out.println("Téléchargement du fichier B...");

        // TODO 1 : Créer deux threads différents.


        // TODO 2 : Démarrer les deux threads.


        // TODO 3 : Attendre leur terminaison avec join().


        System.out.println("Tous les téléchargements sont terminés !");
    }


    // =====================================================
    // EXERCICE 3 : COMPTE À REBOURS
    // Difficulté : Facile
    // =====================================================

    // Objectif :
    // Faire compter deux threads simultanément.
    // Chaque thread doit compter de 5 à 1.
    // Ajouter une pause de 500 ms entre chaque nombre.

    public static void exercise3() throws InterruptedException {

        Runnable countdown = () -> {

            // TODO 1 : Boucle de 5 à 1.


                // TODO 2 : Afficher le nom du thread
                // et la valeur actuelle.


                try {
                    // TODO 3 : Mettre le thread en pause
                    // pendant 500 millisecondes.


                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            // Fin de la boucle
        };

        // TODO 4 : Créer deux threads utilisant
        // le même Runnable.


        // TODO 5 : Démarrer les deux threads.


        // TODO 6 : Attendre leur terminaison.


    }


    // =====================================================
    // EXERCICE 4 : RUNNABLE PARTAGÉ
    // Difficulté : Moyen
    // =====================================================

    // Objectif :
    // Deux threads doivent utiliser exactement
    // la même instance de Runnable.
    //
    // Chaque thread doit afficher son nom
    // et exécuter une boucle de 10 itérations.

    static class SharedTask implements Runnable {

        @Override
        public void run() {

            // TODO 1 : Créer une boucle de 10 itérations.


                // TODO 2 : Afficher le nom du thread
                // et le numéro de l'itération.


            }
        }
    }

    public static void exercise4() throws InterruptedException {

        // TODO 3 : Créer UNE seule instance de SharedTask.


        // TODO 4 : Créer deux threads utilisant cette instance.


        // TODO 5 : Démarrer les deux threads.


        // TODO 6 : Attendre les deux threads avec join().


    }


    // =====================================================
    // EXERCICE 5 : RACE CONDITION (HEAP VS STACK)
    // Difficulté : Moyen
    // =====================================================

    // Objectif :
    // Comprendre pourquoi deux threads qui modifient
    // le même attribut peuvent produire un résultat incorrect.

    static class UnsafeCounter implements Runnable {

        // Attribut partagé dans le Heap.
        private int sharedCounter = 0;

        @Override
        public void run() {

            // Variable locale : propre à chaque invocation.
            int localCounter = 0;

            for (int i = 0; i < 100000; i++) {

                // TODO 1 : Incrémenter localCounter.


                // TODO 2 : Incrémenter sharedCounter.


            }

            System.out.println(
                Thread.currentThread().getName()
                + " | Local : " + localCounter
                + " | Partagé : " + sharedCounter
            );
        }

        public int getCounter() {
            return sharedCounter;
        }
    }

    public static void exercise5() throws InterruptedException {

        UnsafeCounter counter = new UnsafeCounter();

        // TODO 3 : Créer deux threads utilisant
        // le même objet counter.


        // TODO 4 : Démarrer les deux threads.


        // TODO 5 : Attendre leur terminaison.


        // TODO 6 : Afficher le résultat final.
        // Est-il toujours égal à 200000 ?


    }


    // =====================================================
    // EXERCICE 6 : SYNCHRONIZED
    // Difficulté : Moyen
    // =====================================================

    // Objectif :
    // Corriger la Race Condition de l'exercice précédent
    // avec le mot-clé synchronized.

    static class SafeCounter implements Runnable {

        private int counter = 0;

        // TODO 1 : Ajouter synchronized à cette méthode.
        public void increment() {

            counter++;

        }

        @Override
        public void run() {

            for (int i = 0; i < 100000; i++) {

                // TODO 2 : Appeler increment().


            }
        }

        public int getCounter() {
            return counter;
        }
    }

    public static void exercise6() throws InterruptedException {

        SafeCounter counter = new SafeCounter();

        // TODO 3 : Créer deux threads partageant
        // le même SafeCounter.


        // TODO 4 : Démarrer les deux threads.


        // TODO 5 : Attendre leur terminaison.


        // TODO 6 : Afficher le compteur.
        // Le résultat attendu est 200000.


    }


    // =====================================================
    // EXERCICE 7 : ATOMIC INTEGER
    // Difficulté : Moyen
    // =====================================================

    // Objectif :
    // Reproduire l'exercice 6 avec AtomicInteger
    // plutôt qu'avec synchronized.

    static class AtomicCounter implements Runnable {

        private final AtomicInteger counter =
            new AtomicInteger(0);

        @Override
        public void run() {

            for (int i = 0; i < 100000; i++) {

                // TODO 1 : Incrémenter le compteur
                // avec une opération atomique.


            }
        }

        public int getCounter() {
            return counter.get();
        }
    }

    public static void exercise7() throws InterruptedException {

        AtomicCounter counter = new AtomicCounter();

        // TODO 2 : Créer deux threads partageant
        // le même AtomicCounter.


        // TODO 3 : Démarrer les deux threads.


        // TODO 4 : Attendre leur terminaison.


        // TODO 5 : Vérifier que le résultat est 200000.


    }


    // =====================================================
    // EXERCICE 8 : THREAD POOL
    // Difficulté : Intermédiaire
    // =====================================================

    // Objectif :
    // Générer 20 tâches et les exécuter avec un pool
    // contenant seulement 3 threads.
    //
    // Chaque tâche doit afficher son identifiant,
    // attendre 1 seconde et afficher sa terminaison.

    public static void exercise8() throws InterruptedException {

        // TODO 1 : Créer un ExecutorService
        // avec exactement 3 threads.


        // TODO 2 : Créer une boucle de 1 à 20.

            // TODO 3 : Soumettre une tâche avec submit().
            // Chaque tâche doit :
            // - Afficher son identifiant et son thread.
            // - Attendre 1 seconde.
            // - Afficher qu'elle est terminée.


        // TODO 4 : Empêcher les nouvelles soumissions
        // avec shutdown().


        // TODO 5 : Attendre la fin de toutes les tâches
        // avec awaitTermination().


    }


    // =====================================================
    // EXERCICE 9 : TÂCHE PÉRIODIQUE
    // Difficulté : Intermédiaire
    // =====================================================

    // Objectif :
    // Simuler un système qui vérifie le statut d'un serveur
    // toutes les 2 secondes.
    //
    // Arrêter automatiquement la tâche après 10 secondes.

    public static void exercise9() throws InterruptedException {

        // TODO 1 : Créer un ScheduledExecutorService
        // avec un seul thread.


        // TODO 2 : Créer un Runnable qui affiche :
        // "Vérification du serveur..."


        // TODO 3 : Planifier son exécution toutes les
        // 2 secondes avec scheduleAtFixedRate().


        // TODO 4 : Attendre 10 secondes.


        // TODO 5 : Annuler la tâche planifiée.


        // TODO 6 : Fermer le scheduler avec shutdown().


    }


    // =====================================================
    // EXERCICE 10 : VIRTUAL THREADS ET FUTURES
    // Difficulté : Avancé
    // =====================================================

    // Objectif :
    // Simuler 100 requêtes réseau simultanées.
    //
    // Chaque requête :
    // - Possède un identifiant unique.
    // - Attend 1 seconde.
    // - Retourne un résultat sous forme de String.
    //
    // Récupérer les résultats avec Future.

    public static void exercise10() throws Exception {

        // TODO 1 : Créer un exécuteur de Virtual Threads
        // avec newVirtualThreadPerTaskExecutor().


        // TODO 2 : Créer une liste de Future<String>.


        // TODO 3 : Générer 100 tâches.
        // Chaque tâche doit être un Callable<String>.
        //
        // Elle doit :
        // - Attendre 1 seconde.
        // - Retourner "Requête X terminée".


        // TODO 4 : Ajouter chaque Future à la liste.


        // TODO 5 : Parcourir la liste et récupérer
        // chaque résultat avec Future.get().


        // TODO 6 : Afficher le nombre total de requêtes
        // terminées.


        // TODO 7 : Fermer correctement l'exécuteur
        // avec try-with-resources.


    }
}
