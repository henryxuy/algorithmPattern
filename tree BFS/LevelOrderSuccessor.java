import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class LevelOrderSuccessor {
    public static TreeNode findSuccessorOther(TreeNode root, int key) {
        // Given a binary tree and a node, find its level-order successor
        // i.e. the next node in level-order BFS traversal
        Queue<TreeNode> levelQueue = new LinkedList<>();
        boolean resultFlag = false;
        levelQueue.add(root);
        while(!levelQueue.isEmpty()){
            TreeNode current = levelQueue.poll();
            if(resultFlag){
                return current;
            }

            if(current.val == key){
                resultFlag = true;
            }

            if(current.left != null){
                levelQueue.offer(current.left);
            }
            if(current.right != null){
                levelQueue.offer(current.right);
            }
        }

        return null;    
    }

    public static TreeNode findSuccessor(TreeNode root, int key) {
        // Given a binary tree and a node, find its level-order successor
        // i.e. the next node in level-order BFS traversal
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.add(root);
        while(!levelQueue.isEmpty()){
            TreeNode current = levelQueue.poll();
            if(current.left != null){
                levelQueue.offer(current.left);
            }
            if(current.right != null){
                levelQueue.offer(current.right);
            }
            
            if(current.val == key){
                break;
            }
        }

        return levelQueue.peek();    
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        TreeNode result = LevelOrderSuccessor.findSuccessor(root, 12);
        if (result != null)
            System.out.println(result.val + " ");
        result = LevelOrderSuccessor.findSuccessor(root, 9);
        if (result != null)
            System.out.println(result.val + " ");
    }
}