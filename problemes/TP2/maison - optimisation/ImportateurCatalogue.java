import java.util.List;
import java.util.ArrayList;

/**
 * Analyseur d'importation de catalogue fournisseur.
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
public class ImportateurCatalogue<T> {
    
    public List<T> identifierReferencesEnDouble(List<T> fluxImportation) {
        List<T> doublonsDetectes = new ArrayList<>();
        int nombreTotal = fluxImportation.size();
        
        for (int i = 0; i < nombreTotal; i++) {
            for (int j = 0; j < nombreTotal; j++) {
                if (i != j && fluxImportation.get(i).equals(fluxImportation.get(j))) {
                    if (!doublonsDetectes.contains(fluxImportation.get(i))) {
                        doublonsDetectes.add(fluxImportation.get(i));
                    }
                }
            }
        }
        return doublonsDetectes;
    }

    public static void main(String[] args) {
        // N réduit à 20 000 car O(N^2) avec 1 million d'éléments ferait 10^12 opérations (blocage assuré)
        int n = 20_000;
        System.out.println("Génération de " + n + " références produits...");
        List<String> catalogue = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            catalogue.add("REF-" + (i % 15_000)); // Crée des doublons intentionnels
        }

        // Utilisation correcte de l'instance
        ImportateurCatalogue<String> importateur = new ImportateurCatalogue<>();
        
        System.out.println("Lancement de la recherche naïve O(N^2)...");
        long debut = System.currentTimeMillis();
        
        List<String> doublons = importateur.identifierReferencesEnDouble(catalogue);
        
        long fin = System.currentTimeMillis();
        System.out.println("Doublons trouvés : " + doublons.size() + " en " + (fin - debut) + " ms");
    }
}