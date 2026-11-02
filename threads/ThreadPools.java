package threads;
import java.util.concurrent.*;

// =============================================
// 1. TÂCHES SIMULANT DES APPELS À UNE API
// =============================================

class DashboardTasks {

    public static void fetch_stats() {
        System.out.println("Récupération des statistiques...");
        sleep(2000);
        System.out.println("Statistiques chargées !");
    }

    public static void fetch_user() {
        System.out.println("Récupération du profil utilisateur...");
        sleep(3000);
        System.out.println("Profil utilisateur chargé !");
    }

    public static void fetch_transactions() {
        System.out.println("Récupération des transactions...");
        sleep(4000);
        System.out.println("Transactions chargées !");
    }

    // Simule une opération qui prend du temps
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// =============================================
// 2. TÂCHE UTILISÉE POUR LES 100 EXÉCUTIONS
// =============================================

class WorkerTask implements Runnable {

    private final int taskId;

    public WorkerTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {

        System.out.println(
                "Tâche " + taskId +
                        " exécutée par " +
                        Thread.currentThread().getName());

        try {
            // Simule une opération lente :
            // appel réseau, traitement ou accès à un fichier.
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Tâche " + taskId + " terminée !");
    }
}

// =============================================
// 3. PROGRAMME PRINCIPAL
// =============================================

public class ThreadPools {

    public static void main(String[] args) throws Exception {

        // =============================================
        // EXEMPLE 1 : THREAD POOL POUR LE DÉMARRAGE D'UNE UI
        // =============================================

        System.out.println("\n--- DÉMARRAGE DE L'INTERFACE ---");

        // On crée un pool de 3 threads.
        // Le pool centralise la gestion des threads :
        // création, attribution des tâches et réutilisation.
        ExecutorService uiExecutor = Executors.newFixedThreadPool(3);

        // Chaque tâche est soumise au même gestionnaire.
        // Les trois opérations peuvent s'exécuter simultanément.
        uiExecutor.submit(DashboardTasks::fetch_stats);
        uiExecutor.submit(DashboardTasks::fetch_user);
        uiExecutor.submit(DashboardTasks::fetch_transactions);

        // Le thread principal reste libre de continuer.
        System.out.println("Interface en cours de chargement...");

        // On attend la fin des tâches avant de fermer le pool.
        uiExecutor.shutdown();
        uiExecutor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Toutes les données sont chargées !");

        // =============================================
        // EXEMPLE 2 : 100 TÂCHES ET 100 THREADS
        // =============================================

        System.out.println("\n--- EXÉCUTION DE 100 TÂCHES ---");

        // Pool contenant 100 threads de travail.
        ExecutorService executor = Executors.newFixedThreadPool(100);

        // On génère et soumet 100 tâches différentes.
        for (int i = 1; i <= 100; i++) {

            // Chaque tâche possède son propre identifiant.
            Runnable task = new WorkerTask(i);

            // Le pool décide quel thread exécutera la tâche.
            executor.submit(task);
        }

        // Indique qu'aucune nouvelle tâche ne sera soumise.
        // Les tâches déjà soumises continueront leur exécution.
        executor.shutdown();

        // Attend la terminaison de toutes les tâches.
        boolean completed = executor.awaitTermination(
                30, TimeUnit.SECONDS);

        System.out.println(
                completed
                        ? "Les 100 tâches sont terminées !"
                        : "Le délai d'attente est dépassé.");
    }
}
