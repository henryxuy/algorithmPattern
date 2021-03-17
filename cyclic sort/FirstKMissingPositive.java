import java.util.*;

public class FirstKMissingPositive {

    public static List<Integer> findNumbers(int[] nums, int k) {
        List<Integer> missingNumbers = new ArrayList<>();
        HashSet<Integer> outRangeSet = new HashSet<>();
        int i = 0, N = nums.length;

        while(i < N){
            if(nums[i] <= 0){
                i++;
            }
            else if(nums[i] > N){
                if(nums[i] <= N + k){
                    outRangeSet.add(nums[i]);
                }
                i++;
            }
            else if(i != nums[i] - 1 && nums[nums[i] - 1] != nums[i]){
                // not correctly located and not duplicated
                swap(nums[i] - 1, i, nums);
            }
            else{
                // correctly located or duplicated
                i++;
            }
        }
        // add the k missing numbers
        for(i = 0; i < N; i++){
            if(i != nums[i] - 1 && missingNumbers.size() < k){
                missingNumbers.add(i + 1);
            }
        }
        int currentNumber = N + 1;
        while(missingNumbers.size() < k){
            if(!outRangeSet.contains(currentNumber)){
                missingNumbers.add(currentNumber);
            }
            currentNumber++;
        }

        return missingNumbers;
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    } 
    
}
