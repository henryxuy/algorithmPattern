import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
};

class ReverseLevelOrderTraversal {

  public static List<List<Integer>> traverse(TreeNode root) {
    List<List<Integer>> result = new LinkedList<List<Integer>>();
    if (root == null)
      return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      List<Integer> currentLevel = new ArrayList<>(levelSize);
      for (int i = 0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll();
        // add the node to the current level
        currentLevel.add(currentNode.val);
        // insert the children of current node to the queue
        if (currentNode.left != null)
          queue.offer(currentNode.left);
        if (currentNode.right != null)
          queue.offer(currentNode.right);
      }
      // append the current level at the beginning(!!) to get reversed order
      result.add(0, currentLevel);
    }

    return result;
  }

  public static List<List<Integer>> traverseReverse(TreeNode root) {
    List<List<Integer>> result = traverseOriginal(root);
    Collections.reverse(result);

    return result;
  }

  public static List<List<Integer>> traverseOriginal(TreeNode root){
    // loop one layer through countSize
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if(root == null){
      return result;
    }

    Queue<TreeNode> levelQueue = new LinkedList<>();
    levelQueue.add(root);
    while(!levelQueue.isEmpty()){
      List<Integer> tempResultList = new ArrayList<>();
      int layerSize = levelQueue.size();
      for(int i = 0; i < layerSize; i++){
        // one layer per iteration in this loop
        TreeNode current = levelQueue.poll();
        tempResultList.add(current.val);
        if(current.left != null){
          levelQueue.add(current.left);
        }
        if(current.right != null){
          levelQueue.add(current.right);
        }
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
    List<List<Integer>> result = ReverseLevelOrderTraversal.traverse(root);
    System.out.println("Reverse level order traversal: " + result);
  }
}