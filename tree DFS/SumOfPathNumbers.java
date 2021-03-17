import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class SumOfPathNumbers {
    public static int findSumOfPathNumbers(TreeNode root) {
        // each root-to-leaf path represents a number. Find the sum of them.
        List<Integer> tempPath = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();
        int resultSum = 0;

        backtrackHelper(root, tempPath, resultList);

        resultSum = resultList.stream().mapToInt(Integer::intValue).reduce(0, Integer::sum);

        return resultSum;
    }


    private static void backtrackHelper(TreeNode current, List<Integer> path, List<Integer> resultList){
        if(current == null){
            // exit directly for null node
            return;
        }

        path.add(current.val);
        if(current.left == null && current.right == null){
            // leaf node
            int sum = 0;
            for(Integer tempNumber: path){
                sum = sum * 10;
                sum += tempNumber;
            }
            resultList.add(sum);
        }
        else{
            // do backtrack recursion
            backtrackHelper(current.left, path, resultList);
            backtrackHelper(current.right, path, resultList);
        }

        path.remove(path.size() - 1);     // remove the last node (current.val added before in this function call)
    }

    private static int findRootToLeafPathNumbers(TreeNode currentNode, int pathSum) {
        if (currentNode == null)
            return 0;
    
        // calculate the path number of the current node
        pathSum = 10 * pathSum + currentNode.val;
    
        // if the current node is a leaf, return the current path sum.
        if (currentNode.left == null && currentNode.right == null) {
            return pathSum;
        }
    
        // traverse the left and the right sub-tree
        return findRootToLeafPathNumbers(currentNode.left, pathSum) + findRootToLeafPathNumbers(currentNode.right, pathSum);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " + SumOfPathNumbers.findSumOfPathNumbers(root));
    }
}