import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class CountAllPathSum {
    public static int countPaths(TreeNode root, int S) {
        // find all subpaths (not necessarily root to leaf) whose sum = S. Return the number found.
        ArrayList<Integer> tempPath = new ArrayList<>();
        int[] resultArray = new int[1];
        resultArray[0] = 0;

        countHelper(root, S, resultArray, tempPath);
        
        return resultArray[0];
    }

    private static void countHelper(TreeNode current, int targetSum, int[] resultArray, ArrayList<Integer> path){
        if(current == null){
            return;
        }
        path.add(current.val);

        // search all subpaths ending at current
        int tempSum = 0;
        int pathSize = path.size();
        for(int i = pathSize - 1; i >= 0; i--){
            tempSum += path.get(i);
            if(tempSum == targetSum){
                resultArray[0]++;
            }
        }

        countHelper(current.left, targetSum, resultArray, path);
        countHelper(current.right, targetSum, resultArray, path);
       
        path.remove(path.size() - 1);
    }


  
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + CountAllPathSum.countPaths(root, 11));
    }
}
