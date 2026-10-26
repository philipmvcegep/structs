/**
 * ============================================================================
 * COURS : LES PERFORMANCES CACHÉES - ARRAYLIST VS LINKEDLIST
 * ============================================================================
 * 
 * CONCEPTS CLÉS EXPLIQUÉS ICI :
 * 1. La mémoire contiguë (ArrayList) vs les nœuds éparpillés (LinkedList).
 * 2. Le coût des *cache misses* pour le processeur (CPU caching).
 * 3. Le piège mortel de l'accès par index sur une LinkedList (O(n²) au lieu de O(n)).
 * 4. Le coût caché du redimensionnement (resizing) de l'ArrayList.
 * ============================================================================
 */

package ressources.Java.exercices;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVsLinkedListPerformance {

    public static void main(String[] args) {
        System.out.println("=== EXEMPLES : ACCÈS SÉQUENTIEL ===");
        executerExempleAcces();

        System.out.println("\n=== EXERCICES : MESURES DE PERFORMANCE ===");
        exercice01_ParcoursForEach();
        exercice02_ImpactCapaciteInitiale();
        exercice03_InsertionMilieu();
    }

    public static void executerExempleAcces() {
        int taille = 50_000;
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < taille; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long debut = System.nanoTime();
        long somme1 = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            somme1 += arrayList.get(i);
        }
        long tempsArrayList = System.nanoTime() - debut;

        debut = System.nanoTime();
        long somme2 = 0;
        for (int i = 0; i < linkedList.size(); i++) {
            somme2 += linkedList.get(i);
        }
        long tempsLinkedList = System.nanoTime() - debut;

        System.out.println("Temps ArrayList.get(i) : " + (tempsArrayList / 1_000_000) + " ms");
        System.out.println("Temps LinkedList.get(i): " + (tempsLinkedList / 1_000_000) + " ms");
    }

    // =========================================================================
    // EXERCICES À COMPLÉTER PAR LES ÉTUDIANTS
    // =========================================================================

    /**
     * EXERCICE 1 : Mesurer le parcours avec une boucle for-each
     * Consigne : Complétez le code dans les blocs TODO pour parcourir les deux listes 
     * à l'aide d'une boucle for-each et sommer leurs éléments.
     */
    public static void exercice01_ParcoursForEach() {
        int taille = 500_000;
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < taille; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        // --- Benchmark ArrayList ---
        long debutAl = System.nanoTime();
        long totalAl = 0;
        
        // TODO: Écrire la boucle for-each pour ArrayList qui ajoute chaque élément à totalAl

        long tempsAl = System.nanoTime() - debutAl;

        // --- Benchmark LinkedList ---
        long debutLl = System.nanoTime();
        long totalLl = 0;
        
        // TODO: Écrire la boucle for-each pour LinkedList qui ajoute chaque élément à totalLl

        long tempsLl = System.nanoTime() - debutLl;

        System.out.println("[Exo 1 - ForEach] ArrayList : " + (tempsAl / 1_000_000) + " ms | LinkedList : " + (tempsLl / 1_000_000) + " ms");
    }

    /**
     * EXERCICE 2 : Comparer la capacité par défaut vs pré-allouée
     * Consigne : Dans les blocs TODO, ajoutez 2 000 000 d'éléments (boucle for) 
     * dans chacune des deux listes pour observer l'effet des reallocations mémoire.
     */
    public static void exercice02_ImpactCapaciteInitiale() {
        int elements = 2_000_000;

        // --- ArrayList avec capacité par défaut (10) ---
        long debut1 = System.nanoTime();
        List<Integer> alDefaut = new ArrayList<>();
        
        // TODO: Ajouter 'elements' entiers dans alDefaut avec une boucle for

        long tempsDefaut = System.nanoTime() - debut1;

        // --- ArrayList avec capacité pré-allouée ---
        long debut2 = System.nanoTime();
        List<Integer> alPrealloue = new ArrayList<>(elements);
        
        // TODO: Ajouter 'elements' entiers dans alPrealloue avec une boucle for

        long tempsPrealloue = System.nanoTime() - debut2;

        System.out.println("[Exo 2 - Capacité] ArrayList par défaut (capacité 10) : " + (tempsDefaut / 1_000_000) + " ms");
        System.out.println("[Exo 2 - Capacité] ArrayList pré-allouée (" + elements + ") : " + (tempsPrealloue / 1_000_000) + " ms");
    }

    /**
     * EXERCICE 3 : Insertion répétée au milieu de la liste
     * Consigne : Dans les blocs TODO, effectuez une boucle de 10 000 itérations pour insérer
     * un élément au milieu de la liste (`liste.size() / 2`).
     */
    public static void exercice03_InsertionMilieu() {
        int iterations = 10_000;
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        // --- Benchmark ArrayList ---
        long debutAl = System.nanoTime();
        
        // TODO: Faire une boucle de 0 à 'iterations' et insérer au milieu de arrayList (index: arrayList.size() / 2)

        long tempsAl = System.nanoTime() - debutAl;

        // --- Benchmark LinkedList ---
        long debutLl = System.nanoTime();
        
        // TODO: Faire une boucle de 0 à 'iterations' et insérer au milieu de linkedList (index: linkedList.size() / 2)

        long tempsLl = System.nanoTime() - debutLl;

        System.out.println("[Exo 3 - Insertion milieu] ArrayList : " + (tempsAl / 1_000_000) + " ms");
        System.out.println("[Exo 3 - Insertion milieu] LinkedList : " + (tempsLl / 1_000_000) + " ms");
    }
}