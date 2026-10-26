/**
 * ============================================================================
 * COURS 1 (Partie 2) : LE CONTRAT MAUDIT DE EQUALS ET HASHCODE
 * ============================================================================
 * 
 * CONCEPTS CLÉS EXPLIQUÉS ICI :
 * 1. Comment une HashMap utilise le hashCode() pour trouver les seaux (buckets).
 * 2. Le piège d'utiliser un objet personnalisé comme clé sans surcharger les méthodes.
 * 3. Le contrat inviolable : "Si deux objets sont égaux, ils DOIVENT avoir le même hash".
 * 4. La solution moderne de Java : les 'record' (qui génèrent tout automatiquement).
 * ============================================================================
 */

package ressources.Java.exercices;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EqualsEtHashCode {

    public static void main(String[] args) {
        System.out.println("=== EXEMPLES DU COURS ===");
        exemple1_LaMapAveugle();
        exemple2_LeContratRespecte();
        exemple3_LaMagieDesRecords();

        System.out.println("\n=== EXERCICES PRATIQUE ===");
        // Décommentez les lignes au fur et à mesure
        // exercice1_ReparerLaCle();
        // exercice2_TestDeCollision();
        // exercice3_CreationRecord();
    }

    // =========================================================================
    // SECTION 1 : EXEMPLES
    // =========================================================================

    public static void exemple1_LaMapAveugle() {
        // Étudiant SANS equals() ni hashCode() surchargés
        Map<EtudiantLegacy, String> notes = new HashMap<>();
        
        EtudiantLegacy e1 = new EtudiantLegacy("Alice", 1234);
        notes.put(e1, "A+");

        // On essaie de retrouver l'étudiant avec un NOUVEL objet contenant les mêmes données
        EtudiantLegacy e2 = new EtudiantLegacy("Alice", 1234);
        
        String noteRetrouvee = notes.get(e2); // RETOURNE NULL ! Pourquoi ?
        System.out.println("[Exemple 1] Note trouvée (sans equals/hashCode) : " + noteRetrouvee);
        // Explication : Java compare les adresses mémoire (identité), pas le contenu !
    }

    public static void exemple2_LeContratRespecte() {
        // Étudiant AVEC equals() et hashCode() correctement implémentés
        Map<EtudiantPropre, String> notes = new HashMap<>();
        
        EtudiantPropre e1 = new EtudiantPropre("Bob", 5678);
        notes.put(e1, "B+");

        EtudiantPropre e2 = new EtudiantPropre("Bob", 5678);
        
        String noteRetrouvee = notes.get(e2); // Fonctionne parfaitement !
        System.out.println("[Exemple 2] Note trouvée (avec contrat) : " + noteRetrouvee);
    }

    public static void exemple3_LaMagieDesRecords() {
        // Depuis Java 14, les "records" créent automatiquement le constructeur,
        // les getters, le toString(), le equals() et le hashCode() !
        Map<EtudiantRecord, String> notes = new HashMap<>();
        
        EtudiantRecord e1 = new EtudiantRecord("Charlie", 9999);
        notes.put(e1, "A");

        EtudiantRecord e2 = new EtudiantRecord("Charlie", 9999);
        System.out.println("[Exemple 3] Note trouvée via un Record : " + notes.get(e2));
    }


    // =========================================================================
    // SECTION 2 : CLASSES ET RECORDS POUR LES EXEMPLES
    // =========================================================================

    public static class EtudiantLegacy {
        String nom;
        int id;
        public EtudiantLegacy(String nom, int id) { this.nom = nom; this.id = id; }
        // Pas de equals ni hashCode !
    }

    public static class EtudiantPropre {
        String nom;
        int id;

        public EtudiantPropre(String nom, int id) {
            this.nom = nom;
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EtudiantPropre that = (EtudiantPropre) o;
            return id == that.id && Objects.equals(nom, that.nom);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nom, id);
        }
    }

    // Déclaration d'un record (Immuable, propre, compact)
    public record EtudiantRecord(String nom, int id) {}


    // =========================================================================
    // SECTION 3 : EXERCICES POUR LES ÉTUDIANTS
    // =========================================================================

    /**
     * EXERCICE 1 : Réparation d'une clé de HashMap
     * Consigne : La classe 'Livre' ci-dessous est utilisée comme clé dans une Map.
     * Ajoutez-lui les méthodes equals() et hashCode() pour que l'on puisse retrouver
     * un livre dans la Map en se basant sur son ISBN.
     */
    public static void exercice1_ReparerLaCle() {
        // TODO: Implémenter equals() et hashCode() dans la classe LivreUnstable
        Map<LivreUnstable, Integer> stock = new HashMap<>();
        stock.put(new LivreUnstable("978-2-1234", "Java Avancé"), 15);

        Integer qte = stock.get(new LivreUnstable("978-2-1234", "Java Avancé"));
        System.out.println("[Exo 1] Quantité du livre trouvé : " + qte); // Devrait afficher 15
    }

    public static class LivreUnstable {
        String isbn;
        String titre;
        public LivreUnstable(String isbn, String titre) { this.isbn = isbn; this.titre = titre; }
        // Ajoutez equals et hashCode ici !
    }

    /**
     * EXERCICE 2 : Piège de mutation d'une clé
     * Consigne : Créez une classe ou un record, insérez-le dans une HashMap en tant que clé,
     * puis modifiez une de ses propriétés après l'insertion. Essayez de le retrouver.
     * Que se passe-t-il ? (Indice : le hashCode change, mais la Map ne le sait pas).
     */
    public static void exercice2_TestDeCollision() {
        // TODO: Expérimenter la modification d'un objet mutable dans une HashMap
    }

    /**
     * EXERCICE 3 : Migration vers un Record
     * Consigne : Transformez la classe 'PointXY' classique ci-dessous en un 'record'.
     * Vérifiez que le equals et le hashCode fonctionnent de pair.
     */
    public static void exercice3_CreationRecord() {
        // TODO: Convertir PointXY en record et tester l'égalité de deux points ayant les mêmes coordonnées
    }
}