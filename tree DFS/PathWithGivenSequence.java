import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class PathWithGivenSequence {
    public static boolean findPath(TreeNode root, int[] sequence) {
        // Given a binary tree and a path sequence, find if the sequence is present as a root-to-leaf path
        return backtrackHelper(root, sequence, 0);
    }

    private static boolean backtrackHelper(TreeNode current, int[] sequence, int sequenceIndex){
        if(sequenceIndex == sequence.length){
            return current == null;
        }

        if(current == null){
            return sequenceIndex == sequence.length;
        }

        if(current.val == sequence[sequenceIndex]){
            return backtrackHelper(current.left, sequence, sequenceIndex + 1) || backtrackHelper(current.right, sequence, sequenceIndex + 1);
        }
        else{
            return false;
        }

    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 0, 7 }));
        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 1, 6 }));
    }
}