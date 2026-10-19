/**
 * Planificateur de routes logistiques.
 * 
 * Contexte Métier : Une entreprise de livraison modélise son réseau d'entrepôts
 * sous forme de graphe pour vérifier la faisabilité de transport entre sites.
 * 
 * Problème d'analyse : En présence de cycles routiers, le système plante par manque
 * de mémoire (StackOverflow) ou tourne indéfiniment en boucle.
 * 
 * Complexité actuelle : Pire cas infini (non terminant)
 * Complexité recherchée : O(V + E)
 */

public class MoteurRechercheCatalogue<T extends Comparable<T>> {
    
    public static class NoeudProduit<T> {
        public T refProduit;
        public NoeudProduit<T> sousCategorieGauche, sousCategorieDroite;
        public NoeudProduit(T ref) { this.refProduit = ref; }
    }

    public boolean verifierDisponibiliteArticle(NoeudProduit<T> racine, T refRecherchee) {
        if (racine == null) return false;
        boolean presentAGauche = verifierDisponibiliteArticle(racine.sousCategorieGauche, refRecherchee);
        boolean presentADroite = verifierDisponibiliteArticle(racine.sousCategorieDroite, refRecherchee);
        return racine.refProduit.equals(refRecherchee) || presentAGauche || presentADroite;
    }

    public static void main(String[] args) {
        // Construction d'un arbre linéaire (pire des cas pour démontrer la récursion profonde)
        System.out.println("Construction d'un arbre de 1,000,000 de nœuds...");
        NoeudProduit<Integer> racine = new NoeudProduit<>(0);
        NoeudProduit<Integer> courant = racine;
        
        for (int i = 1; i <= 5000; i++) { // Limité à 5000 pour éviter un StackOverflow immédiat en mode naïf récursif complet
            courant.sousCategorieDroite = new NoeudProduit<>(i);
            courant = courant.sousCategorieDroite;
        }

        MoteurRechercheCatalogue<Integer> moteur = new MoteurRechercheCatalogue<>();
        
        long debut = System.currentTimeMillis();
        boolean trouve = moteur.verifierDisponibiliteArticle(racine, 4999);
        long fin = System.currentTimeMillis();

        System.out.println("Article trouvé ? " + trouve);
        System.out.println("Temps d'exécution (Parcours O(N) aveugle) : " + (fin - debut) + " ms");
    }
}
