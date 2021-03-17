import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class ZigzagTraversal {
    public static List<List<Integer>> traverse(TreeNode root) {
        // zigzag traversal: left to right in 1,3,5,... levels, right to left in 2, 4, 6,... levels

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> levelQueue = new LinkedList<>();
        boolean isLeftRight = true;
        levelQueue.add(root);

        while(!levelQueue.isEmpty()){
            int tempSize = levelQueue.size();
            List<Integer> tempResultList = new ArrayList<>();
            for(int i = 0; i < tempSize; i++){
                TreeNode current = levelQueue.poll();
                if(isLeftRight){
                    tempResultList.add(current.val);
                }
                else{
                    tempResultList.add(0, current.val);
                }
                if(current.left != null){
                    levelQueue.add(current.left);
                }
                if(current.right != null){
                    levelQueue.add(current.right);
                }

            }
            if(isLeftRight){
                isLeftRight = false;
            }
            else{
                isLeftRight = true;
            }
            result.add(tempResultList);
        }

        return result;
    }


    public static List<List<Integer>> traverseSimple(TreeNode root) {
        // zigzag traversal: left to right in 1,3,5,... levels, right to left in 2, 4, 6,... levels

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> levelQueue = new LinkedList<>();
        boolean isLeftRight = true;
        levelQueue.add(root);

        while(!levelQueue.isEmpty()){
            int tempSize = levelQueue.size();
            List<Integer> tempResultList = new ArrayList<>();
            for(int i = 0; i < tempSize; i++){
                TreeNode current = levelQueue.poll();
                tempResultList.add(current.val);
                if(current.left != null){
                    levelQueue.add(current.left);
                }
                if(current.right != null){
                    levelQueue.add(current.right);
                }

            }
            if(isLeftRight){
                isLeftRight = false;
            }
            else{
                isLeftRight = true;
                Collections.reverse(tempResultList);
            }
            result.add(tempResultList);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);
        List<List<Integer>> result = ZigzagTraversal.traverse(root);
        System.out.println("Zigzag traversal: " + result);
    }
}