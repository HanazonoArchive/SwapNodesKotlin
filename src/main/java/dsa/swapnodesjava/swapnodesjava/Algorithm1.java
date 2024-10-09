package dsa.swapnodesjava.swapnodesjava;

public class Algorithm1 {
    public static class TreeNode {
        int id;
        TreeNode left;
        TreeNode right;

        public TreeNode(int id) {
            this.id = id;
        }
    }

    private TreeNode root;

    public Algorithm1(int N, int[][] connections) {
        TreeNode[] nodeList = new TreeNode[N];
        for (int i = 0; i < N; i++) {
            nodeList[i] = new TreeNode(i + 1);
        }
        root = nodeList[0];

        // Build the tree from the connections
        for (int i = 0; i < N; i++) {
            int left = connections[i][0];
            int right = connections[i][1];

            if (left > 0) nodeList[i].left = nodeList[left - 1];
            if (right > 0) nodeList[i].right = nodeList[right - 1];
        }
    }

    public String inorder() {
        StringBuilder sb = new StringBuilder();
        inorderHelper(root, sb);
        return sb.toString().trim();
    }

    private void inorderHelper(TreeNode node, StringBuilder sb) {
        if (node != null) {
            inorderHelper(node.left, sb);
            sb.append(node.id).append(" ");
            inorderHelper(node.right, sb);
        }
    }

    public void swap(int target) {
        swap(root, 1, target);
    }

    private void swap(TreeNode node, int depth, int target) {
        if (node == null) return;

        // Swap children nodes at the specified depth
        if (depth % target == 0) {
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }

        // Recur for left and right children
        swap(node.left, depth + 1, target);
        swap(node.right, depth + 1, target);
    }
}
