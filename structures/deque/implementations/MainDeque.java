import java.util.ArrayDeque;
import java.util.Deque;

public class MainDeque {
    public static void main(String[] args) {
        // Utilisation de ArrayDeque (recommandé pour les performances en Java)
        Deque<String> deque = new ArrayDeque<>();

        // Ajout aux deux extrémités
        deque.addFirst("Début 1");
        deque.addLast("Fin 1");
        deque.offerFirst("Début 2");
        deque.offerLast("Fin 2");

        // Consultation sans retrait
        System.out.println("Premier : " + deque.peekFirst()); // Début 2
        System.out.println("Dernier : " + deque.peekLast());   // Fin 1

        // Retrait aux deux extrémités
        System.out.println("Retiré du début : " + deque.removeFirst()); // Début 2
        System.out.println("Retiré de la fin : " + deque.removeLast());   // Fin 2
    }
}