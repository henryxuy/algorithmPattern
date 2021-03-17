import java.util.*;

class SubsetWithDuplicates {

    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        int N = nums.length;
        // subset[startIndex...endIndex] is the range we add new element to
        // endIndex saves the newly added elements in the last time
        int startIndex = 0, endIndex = 0;
        subsets.add(new ArrayList<>());
        for(int i = 0; i < N; i++){
            startIndex = 0;
            // find the same-number range
            if(i > 0 && nums[i] == nums[i - 1]){
                startIndex = endIndex;
            }
            endIndex = subsets.size();
            for(int j = startIndex; j < endIndex; j++){
                List<Integer> listNew = new ArrayList<>(subsets.get(j));
                listNew.add(nums[i]);
                subsets.add(listNew);
            }
        }


        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = SubsetWithDuplicates.findSubsets(new int[] { 1, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = SubsetWithDuplicates.findSubsets(new int[] { 1, 5, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}
