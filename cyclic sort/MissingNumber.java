public class MissingNumber {
    public static int findMissingNumber(int[] nums) {
        // n distinct numbers range from 0 to n (# = n + 1), length of num[] is n
        // find the missing number
        // use cyclic sort (in-place sorting). nums[i] == nums[nums[i]] or nums[i] == i
        // if the number == n, pick it out
        int N = nums.length;
        int i = 0;

        while(i < N) {
            if(nums[i] < N && nums[i] != nums[nums[i]]) {
                // nums[i] not in the correct place
                // we ignore N here. Finally it it exists, it would be placed where the should-exist number is missing.
                swap(nums[i], i, nums);
            }
            else{
                i++;
            }
        }

        for(i = 0; i < N; i++){
            if(nums[i] != i){
                return i;
            }
        }

        return N;
    }
    
    private static void swap(int i, int j, int[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    } 

}
