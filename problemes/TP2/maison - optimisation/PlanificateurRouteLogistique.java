import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Gestionnaire de classement VIP pour marketing (3 pts)
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
public class PlanificateurRouteLogistique<T> {
    
    public boolean validerFaisabiliteRoute(Map<T, List<T>> reseauEntrepots, T entrepotDepart, T entrepotCible) {
        Stack<T> zoneExploration = new Stack<>();
        zoneExploration.push(entrepotDepart);
        
        while (!zoneExploration.isEmpty()) {
            T entrepotActuel = zoneExploration.pop();
            
            if (entrepotActuel.equals(entrepotCible)) {
                return true;
            }
            
            List<T> routesAdjacentes = reseauEntrepots.get(entrepotActuel);
            if (routesAdjacentes != null) {
                for (T voisin : routesAdjacentes) {
                    zoneExploration.push(voisin);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 1_000_000;
        System.out.println("Création d'un réseau linéaire de " + n + " entrepôts...");
        Map<Integer, List<Integer>> reseau = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            List<Integer> voisins = new ArrayList<>();
            if (i < n - 1) {
                voisins.add(i + 1);
            }
            reseau.put(i, voisins);
        }

        PlanificateurRouteLogistique<Integer> planificateur = new PlanificateurRouteLogistique<>();
        
        long debut = System.currentTimeMillis();
        boolean cheminTrouve = planificateur.validerFaisabiliteRoute(reseau, 0, n - 1);
        long fin = System.currentTimeMillis();

        System.out.println("Chemin trouvé vers " + (n - 1) + " ? " + cheminTrouve);
        System.out.println("Temps d'exécution (Naïf) : " + (fin - debut) + " ms");
    }
}