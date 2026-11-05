import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * EXERCICE 4 (Difficile) : Le Coordinateur de Transactions Distribuées (Two-Phase Commit)
 *
 * SCÉNARIO :
 * Moteur de validation d'un système bancaire distribué. Une transaction globale nécessite
 * de valider son état auprès de 12 nœuds de base de données indépendants.
 * Le coordinateur soumet une phase de préparation à tous les nœuds en parallèle via Callable.
 * Chaque nœud simule un délai aléatoire, peut valider (VOTE_COMMIT -> true) ou rejeter
 * (VOTE_ABORT -> false). Le coordinateur collecte les réponses via Future. Si un seul nœud
 * refuse ou échoue, le coordinateur déclenche un ordre d'annulation global (ROLLBACK).
 *
 * OBJECTIFS PÉDAGOGIQUES :
 * - Intégrer l'ensemble des concepts : ExecutorService, Callable, Future, AtomicBoolean et try/catch complexes.
 * - Gérer l'état global partagé de manière atomique sans verrous lourds.
 * - Appliquer la capture stricte de variables finales ou "effectively final" dans des closures d'E/S.
 * - Implémenter une barrière de décision logique collective basée sur des appels asynchrones.
 */
public class DistributedTransactionCoordinatorLab {

    public static void main(String[] args) {
        int totalNodes = 12;
        AtomicBoolean globalDecisionCommit = new AtomicBoolean(true);
        List<Future<Boolean>> nodeFutures = new ArrayList<>();

        final String transactionId = "TX-2026-OCT-9982";

        System.out.println("=== Démarrage de la transaction distribuée : " + transactionId + " ===");

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 1; i <= totalNodes; i++) {
                final int nodeId = i;

                // TODO 1: Soumettre une tâche Callable<Boolean> pour chaque nœud.
                // Chaque nœud doit :
                // - Simuler un traitement réseau aléatoire entre 50ms et 200ms.
                // - Avoir un taux d'échec/refus simulé d'environ 15% (retourner false).
                // - Retourner true si le nœud vote VOTE_COMMIT, false sinon.

                /*
                Future<Boolean> future = executor.submit(() -> {
                    // Logique réseau du nœud
                    return true;
                });
                nodeFutures.add(future);
                */
            }

            // TODO 2: Collecter et analyser les résultats de tous les nœuds via nodeFutures.
            // - Utiliser future.get() pour intercepter et traiter ExecutionException et InterruptedException.
            // - Si un seul nœud retourne false, basculer globalDecisionCommit à false via l'AtomicBoolean.

        } catch (Exception e) {
            System.err.println("Erreur fatale au niveau du coordinateur : " + e.getMessage());
            globalDecisionCommit.set(false);
        }

        // TODO 3: Phase finale de validation (Commit ou Rollback)
        System.out.println("\n=== Bilan final de la transaction " + transactionId + " ===");
        if (globalDecisionCommit.get()) {
            System.out.println("STATUT : TRANSACTION VALIDÉE (COMMIT) sur l'ensemble des 12 nœuds.");
        } else {
            System.out.println("STATUT : TRANSACTION ANNULÉE (ROLLBACK GLOBAL) suite à un refus ou une défaillance.");
        }
    }
}
