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

  // level order traversal using 'next' pointer
    void printLevelOrder() {
        TreeNode nextLevelRoot = this;
        while (nextLevelRoot != null) {
            TreeNode current = nextLevelRoot;
            nextLevelRoot = null;
            while (current != null) {
            System.out.print(current.val + " ");
            if (nextLevelRoot == null) {
                if (current.left != null)
                nextLevelRoot = current.left;
                else if (current.right != null)
                nextLevelRoot = current.right;
            }
            current = current.next;
            }
            System.out.println();
        }
    }
};

class ConnectLevelOrderSiblings {
    public static void connect(TreeNode root) {
        // connect each node with its level order successor
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.offer(root);

        while(!levelQueue.isEmpty()){
            int levelSize = levelQueue.size();
            TreeNode current = levelQueue.peek();
            TreeNode previous = null;
            for(int i = 0; i < levelSize; i++){
                current = levelQueue.poll();
                if(previous != null){
                    previous.next = current;
                }

                if(current.left != null){
                    levelQueue.offer(current.left);
                }
                if(current.right != null){
                    levelQueue.offer(current.right);
                }

                previous = current;
            }
            current.next = null;       // can be ignored, since the node.next is initialized as null
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        ConnectLevelOrderSiblings.connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();
    }
}