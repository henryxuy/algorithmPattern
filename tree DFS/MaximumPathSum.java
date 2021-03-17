import java.util.*;

public class MaximumPathSum {
    private static int maxPathSum;

    public static int findMaximumPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        pathSumHelper(root);

        return maxPathSum;
    }

    private static int pathSumHelper(TreeNode current){
        // return the max subTree path sum
        // and update the path sum using this node as turning point simultaneously
        if(current == null){
            return 0;
        }

        int leftPathSum = pathSumHelper(current.left);
        int rightPathSum = pathSumHelper(current.right);

        // ignore paths with negative sums
        leftPathSum = Math.max(leftPathSum, 0);
        rightPathSum = Math.max(rightPathSum, 0);
        int currentCenteredPathSum = leftPathSum + rightPathSum + current.val;
        maxPathSum = Math.max(currentCenteredPathSum, maxPathSum);

        // if(leftPathSum != 0 && rightPathSum != 0){
        //     // should be non-leaf node to ensure it's a valid turning node
        //     int currentCenteredPathSum = leftPathSum + rightPathSum + current.val;
        //     maxPathSum = Math.max(currentCenteredPathSum, maxPathSum);
        // }
        

        return Math.max(leftPathSum, rightPathSum) + current.val;
    }

    

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));
        
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));
        
        root = new TreeNode(-1);
        root.left = new TreeNode(-3);
        System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));
    }
}
