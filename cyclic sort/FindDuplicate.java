public class FindDuplicate {
    public static int findNumber(int[] nums) {
        // unsorted array containing 'n+1' numbers from the range 1 to n
        // find the duplicates. Only one number has duplicates.
        // range: 1-N, where N = nums.length, nums[i] at nums[i] - 1
        int length = nums.length;
        int i = 0;
        while(i < length) {
            // cyclic sorting
            // Note that we only have one number duplcated. 
            // We will find it if the two swap numbers is the same.
            if(nums[i] != i + 1) {
                if(nums[i] != nums[nums[i] - 1]){
                    swap(nums[i] - 1, i, nums);
                }
                else{
                    return nums[i];     // duplicate number is found.
                }     
            }
            else{
                i++;
            }
        }

        return -1;
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    } 
    
}
