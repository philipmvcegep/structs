import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Utilisation de la LinkedList native de Java avec des entiers
        LinkedList<Integer> list = new LinkedList<>();

        // Insertion au début (équivalent de insertAtFirst)
        list.addFirst(10);
        System.out.println("Linked list after inserting the node:10 at the beginning ");
        System.out.println(list); // Affichage automatique propre de la liste

        // Insertion à la fin (équivalent de insertAtEnd)
        System.out.println("Linked list after inserting the node:20 at the end ");
        list.addLast(20);
        System.out.println(list);

        System.out.println("Linked list after inserting the node:5 at the end ");
        list.addLast(5);
        System.out.println(list);

        System.out.println("Linked list after inserting the node:30 at the end ");
        list.addLast(30);
        System.out.println(list);

        // Insertion à une position spécifique (équivalent de insertAtPosition)
        System.out.println("Linked list after inserting the node:15 at position 2 ");
        list.add(2, 15); // add(index, element)
        System.out.println(list);

        // Suppression du premier nœud (équivalent de deleteFromFirst)
        System.out.println("Linked list after deleting the first node: ");
        list.removeFirst();
        System.out.println(list);

        // Suppression du dernier nœud (équivalent de deleteFromEnd)
        System.out.println("Linked list after deleting the last node: ");
        list.removeLast();
        System.out.println(list);

        // Suppression à une position spécifique (équivalent de deleteAtPosition)
        System.out.println("Linked list after deleting the node at position 1: ");
        list.remove(1); // remove(index)
        System.out.println(list);
    }
}
