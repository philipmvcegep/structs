/**
 * ============================================================================
 * COURS 1 (Partie 3) : LE MYTHE DU PASS-BY-VALUE (PASSAGE PAR VALEUR)
 * ============================================================================
 * 
 * CONCEPTS CLÉS EXPLIQUÉS ICI :
 * 1. La différence fondamentale entre la Stack (pile) et le Heap (tas) en mémoire.
 * 2. Java est TOUJOURS et STRICTEMENT "Pass-by-Value" (par valeur).
 * 3. Pourquoi modifier un attribut d'un objet passé en paramètre fonctionne,
 *    mais réassigner l'objet lui-même (`obj = new ...`) ne change rien à l'extérieur.
 * ============================================================================
 */

package ressources.Java.exercices;

import java.util.ArrayList;
import java.util.List;

public class PassByValue {

    public static void main(String[] args) {
        System.out.println("=== EXEMPLES DU COURS ===");
        exemple1_ModificationAttribut();
        exemple2_EchecDeLaReassignation();

        System.out.println("\n=== EXERCICES PRATIQUE ===");
        // Décommentez les lignes au fur et à mesure
        // exercice1_PiegeDesNombres();
        // exercice2_CorrectionDeListe();
    }

    // =========================================================================
    // SECTION 1 : EXEMPLES
    // =========================================================================

    public static void exemple1_ModificationAttribut() {
        Compteur c = new Compteur(10);
        System.out.println("[Exemple 1] Avant la méthode : " + c.valeur);
        
        incrementer(c); // On passe la VALEUR de la référence (l'adresse mémoire)
        
        System.out.println("[Exemple 1] Après la méthode : " + c.valeur); // Affiche 11 !
        // Pourquoi ? Parce que la copie de la référence pointe vers le même objet sur le Heap.
    }

    public static void incrementer(Compteur comp) {
        comp.valeur++; // Modifie l'objet pointé en mémoire
    }

    public static void exemple2_EchecDeLaReassignation() {
        Compteur c = new Compteur(10);
        System.out.println("[Exemple 2] Avant réassignation : " + c.valeur);
        
        tenterDeRemplacer(c);
        
        System.out.println("[Exemple 2] Après réassignation : " + c.valeur); // Affiche toujours 10 !
        // Pourquoi ? Parce qu'on a juste écrasé la copie locale de la variable, 
        // l'objet d'origine à l'extérieur n'a pas bougé.
    }

    public static void tenterDeRemplacer(Compteur comp) {
        comp = new Compteur(999); // Écrase uniquement la variable locale 'comp'
    }


    // =========================================================================
    // SECTION 2 : CLASSES UTILITAIRES
    // =========================================================================

    public static class Compteur {
        int valeur;
        public Compteur(int valeur) { this.valeur = valeur; }
    }


    // =========================================================================
    // SECTION 3 : EXERCICES POUR LES ÉTUDIANTS
    // =========================================================================

    /**
     * EXERCICE 1 : Piège des types primitifs et des objets enveloppes
     * Consigne : Observez le code ci-dessous. Le nombre va-t-il changer dans le main ?
     * Expliquez pourquoi les types enveloppes (Integer, String, Boolean) sont immuables.
     */
    public static void exercice1_PiegeDesNombres() {
        int original = 5;
        modifierPrimitive(original);
        System.out.println("[Exo 1] Valeur de original : " + original); // Qu'est-ce qui s'affiche ?
    }

    public static void modifierPrimitive(int x) {
        x = 100;
    }

    /**
     * EXERCICE 2 : Écriture d'une méthode de manipulation de liste
     * Consigne : Écrivez une méthode statique 'ajouterElement' qui prend en paramètre
     * une List<String> et une String. La méthode doit ajouter l'élément à la liste.
     * Testez-la dans le main pour prouver que la liste est modifiée à l'extérieur.
     */
    public static void exercice2_CorrectionDeListe() {
        // TODO: Créer une liste, appeler une méthode pour y ajouter un élément, et afficher.
    }
}