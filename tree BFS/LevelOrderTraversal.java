import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

class LevelOrderTraversal {
    public static List<List<Integer>> traverse(TreeNode root){
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


    public static List<List<Integer>> traverseFirstSecond(TreeNode root) {
        // use BFS to do level-order traversal
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null){
          return result;
        }

        Queue<TreeNode> levelQueueFirst = new LinkedList<>();
        Queue<TreeNode> levelQueueSecond = new LinkedList<>();
        boolean firstToSecond = true;
        levelQueueFirst.add(root);
        while(!levelQueueFirst.isEmpty() || !levelQueueSecond.isEmpty()){
            // one level per iteration
            List<Integer> levelResultList = new ArrayList<>();
            if(firstToSecond){
              while(!levelQueueFirst.isEmpty()){
                TreeNode current = levelQueueFirst.poll();
                levelResultList.add(current.val);
                if(current.left != null){
                  levelQueueSecond.add(current.left);
                }
                if(current.right != null){
                  levelQueueSecond.add(current.right);
                }
              }
              firstToSecond = false;
            }
            else{
              while(!levelQueueSecond.isEmpty()){
                TreeNode current = levelQueueSecond.poll();
                levelResultList.add(current.val);
                if(current.left != null){
                  levelQueueFirst.add(current.left);
                }
                if(current.right != null){
                  levelQueueFirst.add(current.right);
                }
              }
              firstToSecond = true;
            }
            result.add(levelResultList);
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
        List<List<Integer>> result = LevelOrderTraversal.traverse(root);
        System.out.println("Level order traversal: " + result);
    }
}