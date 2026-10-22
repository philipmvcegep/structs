import java.util.*;

public class InventoryBenchmark {
    static class Product {
        String sku;
        String name;
        Product(String sku, String name) { this.sku = sku; this.name = name; }
    }

    public static void main(String[] args) {
        int size = 100000;
        List<Product> list = new ArrayList<>();
        Map<String, Product> map = new HashMap<>();

        // Remplissage des structures
        for (int i = 0; i < size; i++) {
            String sku = "SKU-" + i;
            Product p = new Product(sku, "Produit " + i);
            list.add(p);
            map.put(sku, p);
        }

        String targetSku = "SKU-99999"; // On cherche le pire cas (le dernier)
        int iterations = 10000;

        // --- Test 1A : Version Naïve (Liste - O(n) par recherche) ---
        long startNaive = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            for (Product p : list) {
                if (p.sku.equals(targetSku)) break;
            }
        }
        long durationNaive = System.nanoTime() - startNaive;

        // --- Test 1B : Version Optimisée (HashMap - O(1) par recherche) ---
        long startOpt = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            map.get(targetSku);
        }
        long durationOpt = System.nanoTime() - startOpt;

        System.out.println("--- BENCHMARK INVENTAIRE (" + iterations + " recherches) ---");
        System.out.println("Temps Liste (Naïf) : " + (durationNaive / 1_000_000) + " ms");
        System.out.println("Temps HashMap (Optimisé) : " + (durationOpt / 1_000_000) + " ms");
    }
}