/**
 * ============================================================================
 * COURS : LE PIÈGE DU NULL ET LA SOLUTION OPTIONAL<T>
 * ============================================================================
 * 
 * CONCEPTS CLÉS EXPLIQUÉS ICI :
 * 1. Le "Billion-Dollar Mistake" de Tony Hoare et la NullPointerException (NPE).
 * 2. Le style impératif classique (l'escalier de la mort des if imbriqués).
 * 3. L'encapsulation de la présence/absence de valeur avec Optional<T>.
 * 4. L'approche fonctionnelle avec map(), filter() et orElse().
 * ============================================================================
 */

package ressources.Java.exercices;

import java.util.Optional;

public class OptionalEtNull {

    public static void main(String[] args) {
        System.out.println("=== EXEMPLES : NULL VS OPTIONAL ===");
        exemple1_LeCauchemarDuNull();
        exemple2_LaMagieOptional();

        System.out.println("\n=== EXERCICES : REFACTORING AVEC OPTIONAL ===");
        exercice01_RefactoriserNull();
        exercice02_OptionalFiltre();
    }

    public static void exemple1_LeCauchemarDuNull() {
        Client c = new Client(null); // Pas d'adresse
        
        // Le code fragile classique :
        String ville = "INCONNUE";
        if (c != null) {
            Adresse adr = c.getAdresse();
            if (adr != null) {
                String v = adr.getVille();
                if (v != null) {
                    ville = v.toUpperCase();
                }
            }
        }
        System.out.println("[Exemple 1] Ville trouvée (méthode classique) : " + ville);
    }

    public static void exemple2_LaMagieOptional() {
        Client c = new Client(new Adresse("Montréal"));

        // Le même code avec Optional et une chaîne fonctionnelle propre :
        String ville = Optional.ofNullable(c)
                .map(Client::getAdresse)
                .map(Adresse::getVille)
                .map(String::toUpperCase)
                .orElse("INCONNUE");

        System.out.println("[Exemple 2] Ville trouvée (via Optional) : " + ville);
    }

    // =========================================================================
    // CLASSES UTILITAIRES
    // =========================================================================

    public static class Adresse {
        private String ville;
        public Adresse(String ville) { this.ville = ville; }
        public String getVille() { return ville; }
    }

    public static class Client {
        private Adresse adresse;
        public Client(Adresse adresse) { this.adresse = adresse; }
        public Adresse getAdresse() { return adresse; }
    }


    // =========================================================================
    // EXERCICES À COMPLÉTER PAR LES ÉTUDIANTS
    // =========================================================================

    /**
     * EXERCICE 1 : Transformer une recherche potentiellement nulle
     * Consigne : La méthode simule la recherche d'un utilisateur par ID dans une base de données.
     * Complétez le code pour qu'elle retourne un Optional<String> au lieu de retourner null.
     */
    public static Optional<String> trouverNomParId(int id) {
        if (id == 1) {
            return Optional.of("Alice");
        }
        // TODO: Retourner un Optional vide si l'ID n'est pas 1
        return null; 
    }

    public static void exercice01_RefactoriserNull() {
        // Test de votre méthode
        Optional<String> resultat = trouverNomParId(99);
        
        // TODO: Utilisez .orElse("Utilisateur Inconnu") pour afficher le nom ou la valeur par défaut
        String nomFinal = ""; 

        System.out.println("[Exo 1] Résultat recherché : " + nomFinal);
    }

    /**
     * EXERCICE 2 : Filtrer un Optional
     * Consigne : À partir d'un Optional contenant un mot, utilisez la méthode .filter()
     * pour ne conserver le mot que s'il commence par la lettre "A". Sinon, retournez "Mot rejeté".
     */
    public static void exercice02_OptionalFiltre() {
        Optional<String> mot1 = Optional.of("Ananas");
        Optional<String> mot2 = Optional.of("Banane");

        // TODO: Appliquer .filter(m -> m.startsWith("A")) sur mot1, puis .orElse("Mot rejeté")
        String test1 = ""; 

        // TODO: Appliquer .filter(m -> m.startsWith("A")) sur mot2, puis .orElse("Mot rejeté")
        String test2 = ""; 

        System.out.println("[Exo 2] Test 1 (Ananas) : " + test1);
        System.out.println("[Exo 2] Test 2 (Banane) : " + test2);
    }
}