import java.util.*;

public class LogisticsBenchmark {
    static class TimeSlot {
        int start, end;
        TimeSlot(int start, int end) { this.start = start; this.end = end; }
    }

    public static void main(String[] args) {
        int size = 10000; // 10 000 éléments
        List<TimeSlot> slotsNaive = new ArrayList<>();
        List<TimeSlot> slotsOpt = new ArrayList<>();

        // Génération de créneaux SANS CONFLIT (espacés et ordonnés)
        // Cela force le pire cas pour la version naïve qui doit tout vérifier jusqu'à la fin.
        for (int i = 0; i < size; i++) {
            int s = i * 20; // Espacés de 20 unités, durée de 10 -> pas de chevauchement
            slotsNaive.add(new TimeSlot(s, s + 10));
            slotsOpt.add(new TimeSlot(s, s + 10));
        }

        // Pour que la version optimisée ait un vrai travail de tri à faire, 
        // on mélange les éléments de slotsOpt au départ.
        Collections.shuffle(slotsOpt, new Random(42));

        // --- Test 2A : Version Naïve (O(n^2)) ---
        // Pas de conflit = la double boucle va jusqu'au bout (~50 millions d'itérations)
        long startNaive = System.nanoTime();
        boolean conflictNaive = false;
        for (int i = 0; i < slotsNaive.size(); i++) {
            for (int j = i + 1; j < slotsNaive.size(); j++) {
                TimeSlot a = slotsNaive.get(i);
                TimeSlot b = slotsNaive.get(j);
                if (Math.max(a.start, b.start) < Math.min(a.end, b.end)) {
                    conflictNaive = true;
                    break;
                }
            }
            if (conflictNaive) break;
        }
        long durationNaive = System.nanoTime() - startNaive;

        // --- Test 2B : Version Optimisée (Tri + Linéaire -> O(n log n)) ---
        long startOpt = System.nanoTime();
        
        // 1. Tri préalable des éléments mélangés
        slotsOpt.sort(Comparator.comparingInt(s -> s.start));
        
        // 2. Parcours linéaire
        boolean conflictOpt = false;
        for (int i = 0; i < slotsOpt.size() - 1; i++) {
            if (slotsOpt.get(i).end > slotsOpt.get(i + 1).start) {
                conflictOpt = true;
                break;
            }
        }
        long durationOpt = System.nanoTime() - startOpt;

        // --- Affichage des résultats ---
        System.out.println("--- BENCHMARK LOGISTIQUE (" + size + " éléments - Pire cas / Sans conflit) ---");
        System.out.println("Temps Naïf O(n^2) : " + (durationNaive / 1_000_000) + " ms");
        System.out.println("Temps Tri + Linéaire O(n log n) : " + (durationOpt / 1_000_000) + " ms");
    }
}