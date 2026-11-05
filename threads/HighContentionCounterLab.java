import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * EXERCICE 2 (Intermédiaire 1) : Le Compteur de Visites à Haute Contention (synchronized vs AtomicInteger)
 *
 * SCÉNARIO :
 * Un site web à fort trafic enregistre des millions de requêtes par seconde. Le système
 * de journalisation doit compter les clics publicitaires en temps réel en faisant concourir
 * 50 threads virtuels effectuant chacun 10 000 incrémentations. Vous devez comparer
 * l'approche traditionnelle par verrouillage (synchronized) et l'approche lock-free
 * hautement performante (AtomicInteger).
 *
 * OBJECTIFS PÉDAGOGIQUES :
 * - Comprendre et identifier les conditions de compétition (race conditions) sur une ressource partagée.
 * - Mettre en œuvre une section critique sécurisée par synchronized et analyser son impact
 *   sur les threads porteurs (thread pinning).
 * - Utiliser les classes atomiques (java.util.concurrent.atomic) basées sur l'instruction CPU CAS (Compare-And-Swap).
 * - Mesurer et comparer les temps d'exécution (System.currentTimeMillis()).
 */
public class HighContentionCounterLab {

    // Compteur partagé pour l'approche synchronized
    private static int unsafeOrSyncCount = 0;
    private static final Object lockObject = new Object();

    // Compteur partagé pour l'approche lock-free avec AtomicInteger
    private static final AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 50;
        int incrementsPerThread = 10_000;
        long expectedTotal = (long) numberOfThreads * incrementsPerThread;

        // --- PARTIE A : Test avec synchronized ---
        long startTimeSync = System.currentTimeMillis();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, numberOfThreads).forEach(i -> {
                executor.submit(() -> {
                    for (int j = 0; j < incrementsPerThread; j++) {
                        // TODO 1: Implémenter l'incrémentation sécurisée via un bloc synchronized utilisant lockObject
                    }
                });
            });
        } // Attente implicite de la fin de tous les threads virtuels

        long durationSync = System.currentTimeMillis() - startTimeSync;
        System.out.println("Résultat SYNCHRONIZED : " + unsafeOrSyncCount + " (Attendu : " + expectedTotal + ")");
        System.out.println("Temps d'exécution SYNCHRONIZED : " + durationSync + " ms\n");


        // --- PARTIE B : Test avec AtomicInteger ---
        long startTimeAtomic = System.currentTimeMillis();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, numberOfThreads).forEach(i -> {
                executor.submit(() -> {
                    for (int j = 0; j < incrementsPerThread; j++) {
                        // TODO 2: Incrémenter atomiquement la variable atomicCount sans utiliser de verrou explicite
                    }
                });
            });
        }

        long durationAtomic = System.currentTimeMillis() - startTimeAtomic;
        System.out.println("Résultat ATOMIC : " + atomicCount.get() + " (Attendu : " + expectedTotal + ")");
        System.out.println("Temps d'exécution ATOMIC : " + durationAtomic + " ms");
    }
}
