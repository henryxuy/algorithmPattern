import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode(int x) {
        val = x;
        left = right = next = null;
    }
};

public class ConnectAllSiblings {
    public static void connect(TreeNode root) {
        Queue<TreeNode> levelNodes = new LinkedList<>();
        TreeNode parent = null, current = null;
        levelNodes.offer(root);
        while(!levelNodes.isEmpty()){
            // level-order traversal
            int currentSize = levelNodes.size();
            for(int i = 0; i < currentSize; i++){
                current = levelNodes.poll();
                if(current.left != null){
                    levelNodes.offer(current.left);
                }
                if(current.right != null){
                    levelNodes.offer(current.right);
                }
                if(parent != null){
                    parent.next = current;
                }
                parent = current;
            }
        }
        current.next = null;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        ConnectAllSiblings.connect(root);
    
        // level order traversal using 'next' pointer
        TreeNode current = root;
        System.out.println("Traversal using 'next' pointer: ");
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}
