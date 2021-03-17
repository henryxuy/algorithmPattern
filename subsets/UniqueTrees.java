import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

public class UniqueTrees {
    public static List<TreeNode> findUniqueTrees(int n) {
        List<TreeNode> result = new ArrayList<>();

        result = findUniqueTreesRecursive(1, n);

        return result;
    }

    private static List<TreeNode> findUniqueTreesRecursive(int start, int end){
        // Definition of the recursion: generate all BSTs in [start, end]
        // and return the list of head nodes
        List<TreeNode> result = new ArrayList<>();

        // base case: start == end, only one node
        // start > end, null
        if(start > end){
            result.add(null);
            return result;
        }

        for(int i = start; i <= end; i++){
            // use recursion to create tree 
            List<TreeNode> leftBSTs = findUniqueTreesRecursive(start, i - 1);
            List<TreeNode> rightBSTs = findUniqueTreesRecursive(i + 1, end);
            for(TreeNode leftNode: leftBSTs){
                for(TreeNode rightNode: rightBSTs){
                    TreeNode rootNew = new TreeNode(i);
                    rootNew.left = leftNode;
                    rootNew.right = rightNode;
                    result.add(rootNew);
                }
            }
        }
    

        return result;
    }




    // public static List<String> findUniqueTreesStringList(int n) {
    //     List<String> serializedList = new ArrayList<>();

    //     for(int i = 1; i <= n; i++){
    //         // suppose root node is i
    //         List<Integer> leftList = new ArrayList<>();
    //         List<Integer> rightList = new ArrayList<>();
    //         List<String> path = new ArrayList<>();
    //         for(int k = 1; k < i; k++){
    //             leftList.add(k);
    //         }
    //         for(int k = i + 1; k <= n; k++){
    //             rightList.add(k);
    //         }
    //         path.add(String.valueOf(i));
    //         backtrackHelper(serializedList, path, leftList, rightList, 1, n);
    //     }

    //     return serializedList;
    // }


    // private static void backtrackHelper(List<String> serializedResultList, List<String> path,
    //                                     List<Integer> leftChoiceList, List<Integer> rightChoiceList, int pathLength, int n){
    //     // pre-order construct a binary tree
    //     // backtracking: exit condition + path + choice list + loop-recursion-withdrawl
    //     if(pathLength == n){
    //         path.add("#");
    //         path.add("#");
    //         serializedResultList.add(path.toString());
    //         return;
    //     }
    //     // loop-recursion on left and right
    //     // Note: use ',' to separate, use '#' to denote null
    //     if(leftChoiceList.isEmpty()){
    //         path.add("#");
    //     }
    //     else{
    //         for(int i = 0; i < leftChoiceList.size(); i++){
    //             int nodeValue = leftChoiceList.get(i);
    //             path.add(String.valueOf(nodeValue));
    //             List<Integer> newLeftChoiceList = new ArrayList<>();
    //             List<Integer> newRightChoiceList = new ArrayList<>();
    //             for(int k = 0; k < i; k++){
    //                 newLeftChoiceList.add(leftChoiceList.get(k));
    //             }
    //             for(int k = i + 1; k < leftChoiceList.size(); k++){
    //                 newRightChoiceList.add(leftChoiceList.get(k));
    //             }
    //             backtrackHelper(serializedResultList, path, newLeftChoiceList, newRightChoiceList, pathLength + 1, n);
    //             // also need to remove additional '#'
    //             if(newLeftChoiceList.isEmpty()){
    //                 path.remove(path.size() - 1);
    //             }
    //             if(newRightChoiceList.isEmpty()){
    //                 path.remove(path.size() - 1);
    //             }
    //             path.remove(path.size() - 1);
    //         }

    //     }
    //     if(rightChoiceList.isEmpty()){
    //         path.add("#");
    //     }
    //     else{
    //         for(int i = 0; i < rightChoiceList.size(); i++){
    //             int nodeValue = rightChoiceList.get(i);
    //             path.add(String.valueOf(nodeValue));
    //             List<Integer> newLeftChoiceList = new ArrayList<>();
    //             List<Integer> newRightChoiceList = new ArrayList<>();
    //             for(int k = 0; k < i; k++){
    //                 newLeftChoiceList.add(rightChoiceList.get(k));
    //             }
    //             for(int k = i + 1; k < rightChoiceList.size(); k++){
    //                 newRightChoiceList.add(rightChoiceList.get(k));
    //             }
    //             backtrackHelper(serializedResultList, path, newLeftChoiceList, newRightChoiceList, pathLength + 1, n);
    //             if(newLeftChoiceList.isEmpty()){
    //                 path.remove(path.size() - 1);
    //             }
    //             if(newRightChoiceList.isEmpty()){
    //                 path.remove(path.size() - 1);
    //             }
    //             path.remove(path.size() - 1);
    //         }

    //     }
    // }



    
      public static void main(String[] args) {
        List<TreeNode> result = UniqueTrees.findUniqueTrees(3);
        System.out.print("Total trees: " + result.size());

        // List<String> stringList = UniqueTrees.findUniqueTreesStringList(3);
        // for(String str: stringList){
        //     System.out.println(str);
        // }

    }

}
