import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

public class TreePath {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root != null) {
            findPaths(root, "", paths);
        }
        return paths;
    }
    
    private static void findPaths(TreeNode node, String currentPath, List<String> paths) {
        currentPath += node.val;
        
        // Base case: si c'est une feuille, on ajoute le chemin complet
        if (node.left == null && node.right == null) {
            paths.add(currentPath);
            return;
        }
        
        // Étapes récursives : parcourir la gauche et la droite avec le séparateur
        if (node.left != null) {
            findPaths(node.left, currentPath + "->", paths);
        }
        if (node.right != null) {
            findPaths(node.right, currentPath + "->", paths);
        }
    }

    public static void main(String[] args) {
        // Construction d'un arbre binaire de test :
        //        1
        //       / \
        //      2   3
        //       \
        //        5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        List<String> paths = binaryTreePaths(root);
        System.out.println("Chemins de l'arbre : " + paths); // Devrait afficher [1->2->5, 1->3]
    }
}