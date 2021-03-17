import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class LevelAverage {
    public static List<Double> findLevelAverages(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.add(root);
        while(!levelQueue.isEmpty()){
            int levelSize = levelQueue.size();
            double levelSum = 0;
            for(int i = 0; i < levelSize; i++){
                TreeNode current = levelQueue.poll();
                levelSum += current.val;
                if(current.left != null){
                    levelQueue.offer(current.left);
                }
                if(current.right != null){
                    levelQueue.offer(current.right);
                }
            }
            result.add(levelSum / levelSize);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<Double> result = LevelAverage.findLevelAverages(root);
        System.out.print("Level averages are: " + result);
    }
}