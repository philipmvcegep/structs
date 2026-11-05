import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * EXERCICE 1 (Facile) : Le Collecteur de Capteurs Météorologiques Distribués
 *
 * SCÉNARIO :
 * Vous développez un module de diagnostic pour une station météorologique qui doit
 * interroger simultanément 10 capteurs distants fictifs. Chaque capteur possède un
 * délai de réponse variable simulé et retourne un relevé de température sous forme
 * de chaîne de caractères formatée.
 *
 * OBJECTIFS PÉDAGOGIQUES :
 * - Utiliser un exécuteur de threads virtuels (Executors.newVirtualThreadPerTaskExecutor())
 *   encapsulé dans un bloc try-with-resources.
 * - Exploiter l'interface Callable<T> pour récupérer des valeurs de retour et Future<T>
 *   pour la synchronisation.
 * - Mettre en pratique la capture de variables "effectivement finales" (effectively final)
 *   dans les lambdas.
 * - Gérer les exceptions vérifiées (InterruptedException, ExecutionException) avec des blocs try/catch.
 */
public class WeatherCollectorLab {

    public static void main(String[] args) {
        // TODO 1: Déclarer une variable préfixe "effectivement finale" pour identifier les logs (ex: "[METEO-SYS]")

        List<Future<String>> sensorFutures = new ArrayList<>();

        // TODO 2: Initialiser un exécuteur de threads virtuels par tâche dans un bloc try-with-resources
        try (ExecutorService executor = null) {

            for (int i = 1; i <= 10; i++) {
                final int sensorId = i; // Rendu explicitement final pour la capture dans la lambda

                // TODO 3: Soumettre une tâche Callable<String> à l'exécuteur.
                // La tâche doit :
                // - Afficher un message indiquant le démarrage sur le thread courant (Thread.currentThread())
                // - Simuler un délai d'E/S bloquant avec Thread.sleep(80 + (sensorId * 15))
                // - Retourner une chaîne formatée : "Capteur #" + sensorId + " -> Température : " + (20.0 + sensorId) + "°C"

                // Future<String> future = executor.submit(() -> { ... });
                // sensorFutures.add(future);
            }

        } catch (Exception e) {
            System.err.println("Erreur critique lors de l'exécution des tâches : " + e.getMessage());
        }

        // TODO 4: Parcourir la liste des futures, récupérer chaque résultat avec future.get()
        // dans un bloc try/catch séparé, et afficher les relevés collectés.
        System.out.println("\n--- Résultats agrégés des capteurs ---");
    }
}
