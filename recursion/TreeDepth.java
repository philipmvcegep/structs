class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

public class TreeDepth {
    public static int maxDepth(TreeNode root) {
        // Base case: un arbre vide a une profondeur de 0
        if (root == null) {
            return 0;
        }
        
        // Étapes récursives : calculer la profondeur des sous-arbres gauche et droit
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // Combiner les résultats : le max des sous-arbres plus 1 pour le niveau courant
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        // Construction d'un arbre binaire de test :
        //        3
        //       / \
        //      9  20
        //        /  \
        //       15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int depth = maxDepth(root);
        System.out.println("Profondeur maximale de l'arbre : " + depth); // Devrait afficher 3
    }
}