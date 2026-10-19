import java.util.List;
import java.util.ArrayList;

/**
 * Gestionnaire de classement VIP pour marketing.
 * 
 * Contexte Métier : Le département marketing souhaite extraire les K meilleurs clients
 * d'une base de données de fidélité contenant des millions de profils.
 * 
 * Problème d'analyse : Le script réexécute un tri complet de toute la base de données,
 * ce qui gaspille énormément de ressources CPU pour un simple extrait.
 * 
 * Complexité actuelle : O(N^2)
 * Complexité recherchée : O(N log K)
 */
public class GestionnaireClassementVIP<T extends Comparable<T>> {
    
    public List<T> extraireTopClientsFidelite(List<T> donneesClients, int k) {
        List<T> donneesTravail = new ArrayList<>(donneesClients);
        int totalClients = donneesTravail.size();
        
        // Tri à bulles intégral de toute la base
        for (int i = 0; i < totalClients; i++) {
            for (int j = 0; j < totalClients - i - 1; j++) {
                if (donneesTravail.get(j).compareTo(donneesTravail.get(j + 1)) > 0) {
                    T temporaire = donneesTravail.get(j);
                    donneesTravail.set(j, donneesTravail.get(j + 1));
                    donneesTravail.set(j + 1, temporaire);
                }
            }
        }
        
        List<T> topClients = new ArrayList<>();
        for (int i = Math.max(0, totalClients - k); i < totalClients; i++) {
            topClients.add(donneesTravail.get(i));
        }
        return topClients;
    }

    public static void main(String[] args) {
        // N réduit à 15 000 car le tri à bulles O(N^2) avec 1 million d'éléments figerait complètement le PC
        int n = 15_000;
        int k = 10;
        System.out.println("Génération de " + n + " profils clients de fidélité...");
        List<Integer> clients = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            clients.add((int)(Math.random() * 10_000_000)); // Scores de fidélité aléatoires
        }

        // Utilisation correcte de l'instance
        GestionnaireClassementVIP<Integer> gestionnaire = new GestionnaireClassementVIP<>();
        
        System.out.println("Lancement du tri à bulles naïf O(N^2)...");
        long debut = System.currentTimeMillis();
        
        List<Integer> topClients = gestionnaire.extraireTopClientsFidelite(clients, k);

        long fin = System.currentTimeMillis();
        
        System.out.println("Top " + k + " clients extraits avec succès !");
        System.out.println("Temps d'exécution (Naïf) : " + (fin - debut) + " ms");
    }
}