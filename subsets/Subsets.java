import java.util.*;

class Subsets {

    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        int N = nums.length;
        // add the null element
        subsets.add(new ArrayList<>());
        for(int i = 0; i < N; i++){
            int numberNew = nums[i];
            int numCurrentList = subsets.size();
            for(int j = 0; j < numCurrentList; j++){
                List<Integer> tempResult = subsets.get(j);
                List<Integer> resultNew = new ArrayList<>(tempResult);
                resultNew.add(numberNew);
                subsets.add(resultNew);
            }
        }

        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Subsets.findSubsets(new int[] { 1, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = Subsets.findSubsets(new int[] { 1, 5, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}
