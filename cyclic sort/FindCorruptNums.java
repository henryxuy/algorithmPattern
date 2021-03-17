public class FindCorruptNums {
    public static int[] findNumbers(int[] nums) {
        // use cyclic to find the duplicated and missed
        // range: 1-n, index: 0-n-1
        // Invariant for cyclic sort: number i should be at i - 1
        int i = 0;
        int N = nums.length;
        int duplicatedNumber = 0;
        int missingNumber = 0;
        while(i < N){
            if(i != nums[i] - 1 && nums[nums[i] - 1] != nums[i]){
                // see whether it's duplicated. If so, skip it
                swap(nums, i, nums[i] - 1);
                
            }
            else{
                i++;
            }
        }

        for(i = 0; i < N; i++){
            if(nums[i] - 1 != i){
                duplicatedNumber = nums[i];
                missingNumber = i + 1;
            }
        }


        return new int[] { duplicatedNumber, missingNumber };
    }

    private static void swap(int[] nums, int i, int j){
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
    
}
