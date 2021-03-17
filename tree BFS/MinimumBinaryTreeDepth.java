import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class MinimumBinaryTreeDepth {
    public static int findDepth(TreeNode root) {
        // find the minimum depth to the leaf node.
        // i.e. find the shortest path from the root node to the nearest leaf node
        int currentLevel = 1;
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.offer(root);
        while(!levelQueue.isEmpty()){
            int levelSize = levelQueue.size();
            for(int i = 0; i < levelSize; i++){
                TreeNode current = levelQueue.poll();
                if(current.left == null && current.right == null){
                    return currentLevel;
                }
                if(current.left != null){
                    levelQueue.add(current.left);
                }
                if(current.right != null){
                    levelQueue.add(current.right);
                }
            }
            currentLevel++;
        }

        return currentLevel;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree Minimum Depth: " + MinimumBinaryTreeDepth.findDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        System.out.println("Tree Minimum Depth: " + MinimumBinaryTreeDepth.findDepth(root));
    }
}