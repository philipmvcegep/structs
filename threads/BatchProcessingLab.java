import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * EXERCICE 3 (Intermédiaire 2) : Le Traitement par Lots et l'Agrégation de Résultats
 * 
 * SCÉNARIO :
 * Votre application doit traiter un lot de 50 fichiers ou requêtes en arrière-plan 
 * à l'aide de threads virtuels. Chaque tâche effectue un petit traitement, génère un 
 * rapport textuel de succès, et doit l'ajouter dans une file partagée thread-safe 
 * (sans utiliser de verrous explicites comme synchronized).
 * 
 * OBJECTIFS PÉDAGOGIQUES :
 * - Utiliser un exécuteur de threads virtuels dans un bloc try-with-resources pour la gestion des tâches.
 * - Découvrir l'utilisation d'une collection concurrente non bloquante (ConcurrentLinkedQueue) 
 *   pour collecter des données produites par de multiples threads en parallèle.
 * - Gérer proprement la synchronisation implicite des threads virtuels et la visibilité des données.
 */
public class BatchProcessingLab {

    public static void main(String[] args) {
        // TODO 1: Déclarer une file thread-safe pour stocker les rapports de succès de type String
        // Utiliser ConcurrentLinkedQueue<String>
        
        System.out.println("=== Début du traitement par lots des tâches ===");

        // TODO 2: Initialiser un exécuteur de threads virtuels dans un bloc try-with-resources
        try (ExecutorService executor = null) {
            
            // Lancer 50 tâches en parallèle
            IntStream.range(1, 51).forEach(taskId -> {
                // TODO 3: Soumettre une tâche Runnable à l'exécuteur.
                // Chaque tâche doit :
                // - Simuler un traitement de 50 ms (Thread.sleep(50))
                // - Créer un message de rapport : "Tâche #" + taskId + " traitée avec succès."
                // - Ajouter ce message dans la file partagée (ConcurrentLinkedQueue.offer(...))
            });
            
        } // Fin du bloc : l'exécuteur attend automatiquement la fin de toutes les tâches (join implicite)

        System.out.println("=== Tous les lots sont terminés. Affichage des rapports : ===");
        
        // TODO 4: Parcourir et afficher tous les éléments contenus dans la file partagée.
    }
}