import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Analyseur d'importation de catalogue fournisseur (1 pt).
 * 
 * Contexte Métier : Un système ERP importe chaque matin un fichier CSV contenant
 * des milliers de références produits fournies par des tiers pour éviter les doublons.
 * 
 * Problème d'analyse : Le processus prend des heures lors des pics d'importation
 * en raison de l'utilisation d'une double boucle combinée à une recherche linéaire.
 * 
 * Complexité actuelle : O(N^2)
 * Complexité recherchée : O(N)
 */

public class AuditeurCaissePOS<T> {
    
    public List<Boolean> verifierIncidentsCritiques(Stack<T> historiqueTransactions, List<T> codesIncidents) {
        List<Boolean> bilanAudits = new ArrayList<>();
        for (T codeRecherche : codesIncidents) {
            Stack<T> memoireTampon = new Stack<>();
            boolean incidentTrouve = false;
            while (!historiqueTransactions.isEmpty()) {
                T transaction = historiqueTransactions.pop();
                if (transaction.equals(codeRecherche)) {
                    incidentTrouve = true;
                }
                memoireTampon.push(transaction);
            }
            while (!memoireTampon.isEmpty()) {
                historiqueTransactions.push(memoireTampon.pop());
            }
            bilanAudits.add(incidentTrouve);
        }
        return bilanAudits;
    }

    public static void main(String[] args) {
        int n = 1_000_000;
        System.out.println("Chargement d'une pile de " + n + " transactions...");
        Stack<String> pileTransactions = new Stack<>();
        for (int i = 0; i < n; i++) {
            pileTransactions.push("TXN-" + i);
        }

        List<String> ciblesRecherchees = Arrays.asList("TXN-500", "TXN-999999", "TXN-INCONNU");

        AuditeurCaissePOS<String> auditeur = new AuditeurCaissePOS<>();
        
        long debut = System.currentTimeMillis();
        // Version naïve O(M * N) avec 1 million d'éléments dans la pile
        List<Boolean> resultats = auditeur.verifierIncidentsCritiques(pileTransactions, ciblesRecherchees);
        long fin = System.currentTimeMillis();

        System.out.println("Résultats de l'audit : " + resultats);
        System.out.println("Temps d'exécution (Naïf) : " + (fin - debut) + " ms");
    }
}
