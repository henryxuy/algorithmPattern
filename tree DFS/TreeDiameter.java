import java.util.*;



public class TreeDiameter {
    private static int treeDiameter = 0;
    public static int findDiameter(TreeNode root){
        calculateHeight(root);
        
        return treeDiameter;
    }

    private static int calculateHeight(TreeNode currentNode){
        if(currentNode == null){
            return 0;
        }
        int leftTreeHeight = calculateHeight(currentNode.left);
        int rightTreeHeight = calculateHeight(currentNode.right);
        // if the current node doesn't have a left or right subtree, we can't have
        // a path passing through it, since we need a leaf node on each side
        if(leftTreeHeight != 0 && rightTreeHeight != 0){
            int diameter = leftTreeHeight + rightTreeHeight + 1;
            treeDiameter = Math.max(treeDiameter, diameter);
        }

        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }   



    public static int findDiameterSlow(TreeNode root) {
        
        // Queue<TreeNode> levelOrderQueue = new LinkedList<>();
        // HashMap<TreeNode, Integer> heightMap = new HashMap<>();
        // int height = 0;

        // while(!levelOrderQueue.isEmpty()){
        //     int currentSize = levelOrderQueue.size();
        //     for(int i = 0; i < currentSize; i++){
        //         TreeNode currentNode = levelOrderQueue.poll();
        //         heightMap.put(currentNode, height);
        //         if(currentNode.left != null){
        //             levelOrderQueue.offer(currentNode.left);
        //         }
        //         if(currentNode.right != null){
        //             levelOrderQueue.offer(currentNode.right);
        //         }
        //     }
        //     height++;
        // }

        int[] maxHeight = new int[1];
        maxHeight[0] = 0;
        diameterHelper(root, maxHeight);

        return maxHeight[0];
    }

    private static void diameterHelper(TreeNode current, int[] maxHeight){
        if(current == null){
            return;
        }
        int currentDiameter = subTreeLength(current.left) + subTreeLength(current.right) + 1;
        if(currentDiameter > maxHeight[0]){
            maxHeight[0] = currentDiameter;
        }

        diameterHelper(current.left, maxHeight);
        diameterHelper(current.right, maxHeight);
    }


    private static int subTreeLength(TreeNode current){
        if(current == null){
            return 0;
        }

        return 1 + Math.max(subTreeLength(current.left), subTreeLength(current.right));
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
    }
}
