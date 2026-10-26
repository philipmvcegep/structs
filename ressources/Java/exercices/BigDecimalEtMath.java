/**
 * ============================================================================
 * COURS : LE PIÈGE DES MATHÉMATIQUES FLOTTANTES (DOUBLE VS BIGDECIMAL)
 * ============================================================================
 * 
 * CONCEPTS CLÉS EXPLIQUÉS ICI :
 * 1. La norme IEEE 754 et l'impossibilité de représenter parfaitement certains décimaux en binaire.
 * 2. Le bug classique : 0.1 + 0.2 != 0.3 en double.
 * 3. L'utilisation de BigDecimal pour les calculs financiers, statistiques et de précision.
 * ============================================================================
 */

package ressources.Java.exercices;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalEtMath {

    public static void main(String[] args) {
        System.out.println("=== EXEMPLES : LA FAILLE DES FLOTTANTS ===");
        exemple1_LaFailleDouble();
        exemple2_LaPrecisionBigDecimal();

        System.out.println("\n=== EXERCICES : CALCULS FINANCIERS PRÉCIS ===");
        exercice01_CalculFacture();
        exercice02_ArrondiStrict();
    }

    public static void exemple1_LaFailleDouble() {
        double a = 0.1;
        double b = 0.2;
        double resultat = a + b;

        System.out.println("[Exemple 1] 0.1 + 0.2 avec double = " + resultat);
        // Affiche 0.30000000000000004 à cause du stockage binaire !
    }

    public static void exemple2_LaPrecisionBigDecimal() {
        // Toujours instancier BigDecimal avec une String, jamais un double directement !
        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("0.2");
        BigDecimal resultat = a.add(b);

        System.out.println("[Exemple 2] 0.1 + 0.2 avec BigDecimal = " + resultat);
        // Affiche exactement 0.3
    }


    // =========================================================================
    // EXERCICES À COMPLÉTER PAR LES ÉTUDIANTS
    // =========================================================================

    /**
     * EXERCICE 1 : Calcul de facture avec taxes
     * Consigne : Un client achète un produit à 49.99$. Calculez le montant total avec 
     * une taxe de 14.975% (0.14975) en utilisant BigDecimal.
     */
    public static void exercice01_CalculFacture() {
        BigDecimal prixInitial = new BigDecimal("49.99");
        BigDecimal tauxTaxe = new BigDecimal("0.14975");

        // TODO: Multiplier prixInitial par tauxTaxe pour trouver le montant de la taxe
        BigDecimal montantTaxe = BigDecimal.ZERO; 

        // TODO: Additionner prixInitial et montantTaxe pour obtenir le total
        BigDecimal total = BigDecimal.ZERO; 

        System.out.println("[Exo 1] Total de la facture : " + total);
    }

    /**
     * EXERCICE 2 : Arrondi strict et gestion des échelles (Scale)
     * Consigne : Divisez 10 par 3 en utilisant BigDecimal. 
     * Comme la division donne un nombre infini (3.33333...), appliquez un arrondi à 2 décimales 
     * en mode RoundingMode.HALF_UP.
     */
    public static void exercice02_ArrondiStrict() {
        BigDecimal dividende = new BigDecimal("10");
        BigDecimal diviseur = new BigDecimal("3");

        // TODO: Effectuer la division avec (diviseur, scale, RoundingMode.HALF_UP)
        BigDecimal resultatDivision = BigDecimal.ZERO; 

        System.out.println("[Exo 2] 10 divisé par 3 (arrondi à 2 décimales) : " + resultatDivision);
    }
}