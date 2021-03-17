import java.util.*;

public class RightViewTree {
    public static List<TreeNode> traverse(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Queue<TreeNode> leverOrderQueue = new LinkedList<>();
        leverOrderQueue.offer(root);
        while(!leverOrderQueue.isEmpty()){
            int levelSize = leverOrderQueue.size();
            for(int i = 0; i < levelSize; i++){
                TreeNode currentNode = leverOrderQueue.poll();
                if(i == 0){
                    result.add(currentNode);
                }
                if(currentNode.right != null){
                    leverOrderQueue.offer(currentNode.right);
                }
                if(currentNode.left != null){
                    leverOrderQueue.offer(currentNode.left);
                }
            }
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
        root.left.left.left = new TreeNode(3);
        List<TreeNode> result = RightViewTree.traverse(root);
        for (TreeNode node : result) {
            System.out.print(node.val + " ");
        }
    }
}
