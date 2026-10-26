/**
 * ============================================================================
 * COURS 1 (Partie 1) : LES GÉNÉRIQUES, LE TYPE ERASURE ET LA JVM
 * ============================================================================
 * 
 * CONCEPTS CLÉS EXPLIQUÉS ICI :
 * 1. Le rôle de la JVM et la compilation du code Java en bytecode.
 * 2. Le problème des collections pré-Java 5 (les casts explicites et ClassCastException).
 * 3. L'effacement de types (Type Erasure) : le grand mensonge de Java.
 * 4. Création de classes et méthodes génériques avec contraintes (Bounds).
 * ============================================================================
 */


package ressources.Java.exercices;

import java.util.ArrayList;
import java.util.List;

public class GeneriquesEtTypeErasure {

    public static void main(String[] args) {
        System.out.println("=== EXEMPLES DU COURS ===");
        exemple1_LeCoupDesCastsLegacy();
        exemple2_ClasseGeneriqueMaison();
        exemple3_MethodesGeneriquesEtContraintes();

        System.out.println("\n=== EXERCICES PRATIQUE ===%n");
        // Décommentez les lignes au fur et à mesure de vos résolutions d'exercices
        // exercice1_CorrectionLegacy();
        // exercice2_LeSwapMagique();
        // exercice3_PreuveTypeErasure();
    }

    // =========================================================================
    // SECTION 1 : EXEMPLES
    // =========================================================================

    public static void exemple1_LeCoupDesCastsLegacy() {
        // AVANT JAVA 5 (Sans génériques) :
        // Les collections ne stockaient que des "Object". Il fallait caster à la main.
        // C'était une source infinie de bugs plantant en production (ClassCastException).
        
        List rawList = new ArrayList();
        rawList.add("Bonjour");
        rawList.add(42); // Oups, on peut mettre n'importe quoi !

        // Le piège : Le compilateur laisse passer, mais ça plante à l'exécution :
        // String texte = (String) rawList.get(1); // BOOM ! ClassCastException

        // AVEC LES GÉNÉRIQUES :
        // Le compilateur vérifie les types et injecte les casts automatiquement.
        List<String> safeList = new ArrayList<>();
        safeList.add("Bonjour Java");
        // safeList.add(42); // ERREUR DE COMPILATION : impossible d'ajouter un int !
        
        String texteSecurise = safeList.get(0); // Pas besoin de cast explicite !
        System.out.println("[Exemple 1] Texte récupéré proprement : " + texteSecurise);
    }

    public static void exemple2_ClasseGeneriqueMaison() {
        // Utilisation d'une classe générique personnalisée (Boite<T>)
        Boite<String> boiteDeNotes = new Boite<>("Examen de structures de données");
        Boite<Integer> boiteDeScores = new Boite<>(95);

        System.out.println("[Exemple 2] Boite de texte : " + boiteDeNotes.getContenu());
        System.out.println("[Exemple 2] Boite de score : " + boiteDeScores.getContenu());
    }

    public static void exemple3_MethodesGeneriquesEtContraintes() {
        // Appel d'une méthode générique avec une contrainte (T doit être Comparable)
        Integer maxInt = Utilitaire.trouverMax(15, 42);
        String maxStr = Utilitaire.trouverMax("Zèbre", "Tortue"); // Par ordre alphabétique

        System.out.println("[Exemple 3] Max entre 15 et 42 : " + maxInt);
        System.out.println("[Exemple 3] Max entre Zèbre et Tortue : " + maxStr);
    }


    // =========================================================================
    // SECTION 2 : CLASSES ET UTILITAIRES POUR LES EXEMPLES
    // =========================================================================

    // Une classe générique simple
    public static class Boite<T> {
        private T contenu;

        public Boite(T contenu) {
            this.contenu = contenu;
        }

        public T getContenu() {
            return contenu;
        }

        public void setContenu(T contenu) {
            this.contenu = contenu;
        }
    }

    public static class Utilitaire {
        // Méthode générique avec une contrainte : <T extends Comparable<T>>
        // Cela garantit que les objets peuvent être comparés entre eux.
        public static <T extends Comparable<T>> T trouverMax(T a, T b) {
            return (a.compareTo(b) > 0) ? a : b;
        }
    }


    // =========================================================================
    // SECTION 3 : EXERCICES POUR LES ÉTUDIANTS
    // =========================================================================

    /**
     * EXERCICE 1 : Le nettoyage de code legacy
     * Consigne : Le code ci-dessous utilise une liste non générique (brute).
     * Modifiez-le pour utiliser une List<Double> sécurisée, puis calculez la moyenne
     * sans avoir besoin de faire de cast (Double).
     */
    public static void exercice1_CorrectionLegacy() {
        // TODO: Remplacer ArrayList brute par List<Double>
        List notes = new ArrayList();
        notes.add(85.5);
        notes.add(90.0);
        notes.add(78.5);

        double somme = 0;
        for (int i = 0; i < notes.size(); i++) {
            // TODO: Retirer le cast explicite devenu inutile grâce aux génériques
            Double note = (Double) notes.get(i);
            somme += note;
        }
        
        System.out.println("Moyenne : " + (somme / notes.size()));
    }

    /**
     * EXERCICE 2 : Création d'une méthode de permutation (Swap) générique
     * Consigne : Écrivez une méthode générique statique nommée 'echanger' qui prend
     * en paramètre un tableau de type générique T, et deux indices i et j,
     * et permute les éléments du tableau à ces indices.
     * Testez-la avec un tableau de String et un tableau d'Integer.
     */
    public static void exercice2_LeSwapMagique() {
        // TODO: Implémenter la méthode 'echanger' plus bas dans la classe
        // String[] mots = {"chat", "chien", "oiseau"};
        // echanger(mots, 0, 2);
    }

    /**
     * EXERCICE 3 : L'énigme du Type Erasure (Réflexion JVM)
     * Consigne : Écrivez une petite méthode qui crée un List<String> et un List<Integer>.
     * Vérifiez avec l'opérateur '==' ou 'getClass()' si les deux listes partagent
     * la même classe à l'exécution (Runtime). Qu'en concluez-vous sur l'effacement de types ?
     */
    public static void exercice3_PreuveTypeErasure() {
        // TODO: Comparer list1.getClass() et list2.getClass()
    }
}