import java.util.*;

public class FindAllDuplicate {
    public static List<Integer> findNumbers(int[] nums) {
        // range: 1 - n(length). Cyclic sort: num[i] should be at num[i] - 1
        // find duplicates (only duplicates twice) if the elements we want to swap are the same
        // i++ instead of return when find duplicates
        List<Integer> duplicateNumbers = new ArrayList<>();
        int i = 0, N = nums.length;
        while(i < N){
            if(nums[i] != i + 1){
                if(nums[i] != nums[nums[i] - 1]){
                    swap(i, nums[i] - 1, nums);
                }
                else{
                    if(!duplicateNumbers.contains(nums[i])){
                        duplicateNumbers.add(nums[i]);
                    }
                    i++;
                }
            }
            else{
                i++;
            }
        }

        return duplicateNumbers;
    }

    public static List<Integer> findNumbersOther(int[] nums) {
        // We can add without check in this way, since the duplicates only exist twice.
        int i = 0;
        while (i < nums.length) {
          if (nums[i] != nums[nums[i] - 1])
            swap(i, nums[i] - 1, nums);
          else
            i++;
        }
    
        List<Integer> duplicateNumbers = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
          if (nums[i] != i + 1)
            duplicateNumbers.add(nums[i]);
        }
    
        return duplicateNumbers;
    }




    private static void swap(int i, int j, int[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    } 
}
