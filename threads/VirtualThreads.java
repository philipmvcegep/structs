package threads;
import java.util.concurrent.*;

// =====================================================
// 1. CRÉATION D'UN VIRTUAL THREAD
// =====================================================


public class VirtualThreads {

    public static void main(String[] args) throws Exception {

        // Crée et démarre un Virtual Thread.
        // Contrairement à un Platform Thread, il est léger
        // et géré par l'ordonnanceur de la JVM.
        Thread virtualThread = Thread.ofVirtual().start(() -> {

            System.out.println(
                "Exécution sur : " + Thread.currentThread()
            );

            try {
                // Simule une opération bloquante.
                // La JVM peut détacher le Virtual Thread
                // de son Carrier Thread pendant cette attente.
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Tâche terminée !");
        });

        // Attend la fin du Virtual Thread.
        virtualThread.join();


        // =====================================================
        // 2. RELATION AVEC LES PROMISES (JAVASCRIPT)
        // =====================================================

        // En JavaScript :
        //
        // async function fetchUser() {
        //     const response = await fetch("/api/user");
        //     return response.json();
        // }
        //
        // await attend le résultat d'une Promise.
        // Le moteur JavaScript peut traiter d'autres événements
        // pendant cette attente.

        // En Java, on peut utiliser un Virtual Thread
        // pour écrire une logique similaire de manière synchrone.
        Thread.startVirtualThread(() -> {

            String user = fetchUser();

            System.out.println(user);
        }).join();


        // =====================================================
        // 3. CHARGEMENT SIMULTANÉ DES DONNÉES D'UNE INTERFACE
        // =====================================================

        // Équivalent conceptuel de Promise.all() en JavaScript :
        //
        // const [stats, user, transactions] =
        //     await Promise.all([
        //         fetchStats(),
        //         fetchUser(),
        //         fetchTransactions()
        //     ]);

        // Crée un exécuteur qui utilise les Virtual Threads.
        // Chaque tâche soumise reçoit son propre Virtual Thread.
        try (var executor =
                 Executors.newVirtualThreadPerTaskExecutor()) {

            // Soumission des trois tâches sans attendre
            // la fin des précédentes.
            Future<String> stats =
                executor.submit(() -> fetchStats());

            Future<String> user =
                executor.submit(() -> fetchUser());

            Future<String> transactions =
                executor.submit(() -> fetchTransactions());

            System.out.println("Chargement du dashboard...");

            // get() récupère le résultat.
            // Si nécessaire, le thread appelant attend sa disponibilité.
            System.out.println(stats.get());
            System.out.println(user.get());
            System.out.println(transactions.get());

            System.out.println("Dashboard prêt !");
        }


        // =====================================================
        // 4. CRÉATION DE 1000 TÂCHES AVEC DES VIRTUAL THREADS
        // =====================================================

        // Chaque tâche possède son propre Virtual Thread.
        // Les Virtual Threads permettent de gérer beaucoup
        // de tâches bloquantes avec peu de threads natifs.
        try (var executor =
                 Executors.newVirtualThreadPerTaskExecutor()) {

            // Génère 1000 tâches différentes.
            for (int i = 1; i <= 1000; i++) {

                final int taskId = i;

                executor.submit(() -> {

                    System.out.println(
                        "Tâche " + taskId +
                        " exécutée par " +
                        Thread.currentThread()
                    );

                    try {
                        // Simule une requête réseau lente.
                        // Pendant cette attente, le Virtual Thread
                        // peut libérer son Carrier Thread.
                        Thread.sleep(2000);

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println(
                        "Tâche " + taskId + " terminée"
                    );
                });
            }

            // La fermeture de l'exécuteur attend
            // la fin des tâches soumises.
        }

        System.out.println("1000 tâches terminées !");


        // =====================================================
        // 5. COMPARAISON AVEC COMPLETABLEFUTURE
        // =====================================================

        // CompletableFuture représente un résultat futur.
        // Il permet de composer des opérations asynchrones.
        //
        // Il est conceptuellement proche des Promises JavaScript.

        CompletableFuture
            .supplyAsync(() -> fetchUser())
            .thenApply(user -> user.toUpperCase())
            .thenAccept(System.out::println);

        // Avec un Virtual Thread, on peut écrire
        // la même logique de manière séquentielle.
        Thread.startVirtualThread(() -> {

            String result = fetchUser().toUpperCase();

            System.out.println(result);

        }).join();
    }


    // =====================================================
    // MÉTHODES SIMULANT DES APPELS À UNE API
    // =====================================================

    static String fetchUser() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return "Utilisateur récupéré !";
    }

    static String fetchStats() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return "Statistiques récupérées !";
    }

    static String fetchTransactions() {

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        return "Transactions récupérées !";
    }
}

// =====================================================
// RÉSUMÉ DES CONCEPTS
// =====================================================
//
// 1. Virtual Threads : threads légers gérés par la JVM.
//    Disponibles officiellement depuis Java 21.
//
// 2. Promises vs Virtual Threads :
//    - Promise : représente un résultat futur.
//    - Virtual Thread : représente un contexte d'exécution léger.
//    - CompletableFuture : permet de composer des opérations
//      asynchrones en Java.
//
// 3. Concurrence : plusieurs tâches peuvent attendre leurs
//    résultats simultanément sans monopoliser chacune un thread OS.
//
// 4. Scalabilité : idéal pour les applications qui gèrent
//    beaucoup de requêtes réseau et d'opérations I/O.
//
// 5. Limite : les Virtual Threads n'accélèrent pas les calculs
//    CPU intensifs. Le parallélisme reste limité par le matériel.
