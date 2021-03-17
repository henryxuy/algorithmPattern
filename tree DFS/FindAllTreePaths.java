import java.util.*;
import java.util.stream.Collectors;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class FindAllTreePaths {
    public static List<Integer> findMaxPath(TreeNode root) {
        // find all root-to-leaf paths such that its path sum = sum
        List<Integer> maxSumPath = new ArrayList<>();
        List<Integer> onePath = new ArrayList<>();
        int[] maxSumArray = new int[1];
        maxSumArray[0] = 0;

        recursiveHelperMaxSum(root, 0, onePath, maxSumPath, maxSumArray);
        // System.out.println(maxSumPath);
        return maxSumPath;
    }

    private static void recursiveHelperMaxSum(TreeNode current, int currentSum, List<Integer> path, List<Integer> maxSumPath, int[] maxSum){
        if(current == null){
            return;
        }
        // backtracking: do the action (add the current into the path)
        path.add(current.val);
        currentSum += current.val;
        // if the current node is leaf, and is larger than current maxSum, we add the path to the result list
        if(current.left == null && current.right == null && currentSum >= maxSum[0]){
                maxSum[0] = currentSum;
                maxSumPath.clear();
                maxSumPath.addAll(path);
        }
        else{
            // do bfs recursively
            recursiveHelperMaxSum(current.left, currentSum, path, maxSumPath, maxSum);
            recursiveHelperMaxSum(current.right, currentSum, path, maxSumPath, maxSum);
        }
        // remove the action while we are going up the recursive call stack.
        path.remove(path.size() - 1);
        currentSum -= current.val;
    }



    public static List<List<Integer>> findPathsOther(TreeNode root, int sum) {
        // find all root-to-leaf paths such that its path sum = sum
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> onePath = new ArrayList<>();

        recursiveHelper(root, sum, onePath, allPaths);
        return allPaths;
    }

    
    private static void recursiveHelper(TreeNode current, int targetSum, List<Integer> path, List<List<Integer>> allPaths){
        if(current == null){
            return;
        }
        // backtracking: do the action (add the current into the path)
        path.add(current.val);
        // if the current node is leaf, and meets the targetSum, we add the path to the result list
        if(current.left == null && current.right == null && current.val == targetSum){
            allPaths.add(new ArrayList<Integer>(path));
        }
        else{
            // do bfs recursively
            recursiveHelper(current.left, targetSum - current.val, path, allPaths);
            recursiveHelper(current.right, targetSum - current.val, path, allPaths);
        }
        // remove the action while we are going up the recursive call stack.
        path.remove(path.size() - 1);
    }
    

    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        // find all root-to-leaf paths such that its path sum = sum
        List<List<Integer>> allPaths = new ArrayList<>();
        List<TreeNode> onePath = new ArrayList<>();

        backtrackHelper(root, sum, onePath, allPaths);
        return allPaths;
    }

    private static void backtrackHelper(TreeNode current, int targetSum, List<TreeNode> path, List<List<Integer>> allPaths){
        // exiting condition: leaf node
        if(current.left == null && current.right == null){
            if(targetSum == current.val){
                List<Integer> tempResultList = new ArrayList<>();
                // for(TreeNode node: path){
                //     tempResultList.add(node.val);
                // }
                tempResultList.addAll(path.stream().mapToInt(o -> o.val).boxed().collect(Collectors.toList()));
                tempResultList.add(current.val);

                allPaths.add(tempResultList);     // avoid reference puzzle
            }
            return;
        }

        // loop-recursion (Here only 2 choices, so we donot need loop)
        if(current.left != null){
            path.add(current);
            backtrackHelper(current.left, targetSum - current.val, path, allPaths);
            path.remove(current);
        }

        if(current.right != null){
            path.add(current);
            backtrackHelper(current.right, targetSum - current.val, path, allPaths);
            path.remove(current);
        }
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = FindAllTreePaths.findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);

        List<Integer> maxSumPath = FindAllTreePaths.findMaxPath(root);
        System.out.println("Tree paths with max sum : " + maxSumPath);
    }
}