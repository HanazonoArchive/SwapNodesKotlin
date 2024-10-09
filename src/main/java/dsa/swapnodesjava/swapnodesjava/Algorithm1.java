package dsa.swapnodesjava.swapnodesjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Algorithm1 {

    public static class TreeNode {
        int id;
        TreeNode left;
        TreeNode right;

        public TreeNode(int id) {
            this.id = id;
        }
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        TreeNode root = buildTree(indexes);
        List<List<Integer>> result = new ArrayList<>();

        for (int k : queries) {
            swap(root, 1, k);
            result.add(inorderTraversal(root));
        }

        return result;
    }

    private static TreeNode buildTree(List<List<Integer>> indexes) {
        int n = indexes.size();
        TreeNode[] nodeList = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodeList[i] = new TreeNode(i + 1);
        }

        for (int i = 0; i < n; i++) {
            int left = indexes.get(i).get(0);
            int right = indexes.get(i).get(1);

            if (left > 0) nodeList[i].left = nodeList[left - 1];
            if (right > 0) nodeList[i].right = nodeList[right - 1];
        }

        return nodeList[0]; // Return the root of the tree
    }

    private static void swap(TreeNode node, int depth, int target) {
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

    private static List<Integer> inorderTraversal(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(node, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            inorderHelper(node.left, result);
            result.add(node.id);
            inorderHelper(node.right, result);
        }
    }
}
