import java.util.*;

class AllMissingNumbers {

    public static List<Integer> findNumbers(int[] nums) {
        // range: 1-N, where N = nums.length, nums[i] at nums[i] - 1
        // array can have duplicates
        List<Integer> missingNumbers = new ArrayList<>();
        int length = nums.length;
        int i = 0;
        while(i < length) {
            // cyclic sorting
            // each loop, make sure nums[i] is placed at the correct position nums[i] - 1
            if(nums[i] - 1 < length && nums[i] != nums[nums[i] - 1]) {
                swap(nums[i] - 1, i, nums);     // if not at the correct position, swap nums[i] to nums[i] - 1
            }
            else{
                i++;
            }
        }

        for(i = 0; i < length; i++){
            if(nums[i] != i + 1){
                missingNumbers.add(i + 1);
            }
        }

        return missingNumbers;
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    } 

}