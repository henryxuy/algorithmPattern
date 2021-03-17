public class FirstSmallestMissingPositive {
    public static int findNumber(int[] nums) {
        // may have out-range numbers and negative numbers, skip them !
        // in range numbers: 1 to N
        // invariant of cyclic sort: num[i] should be at num[i] - 1, i == num[i] - 1
        int i = 0;
        int N = nums.length;
        while(i < N){
            if(nums[i] - 1 < 0 || nums[i] > N){
                i++;
            }
            else if(i != nums[i] - 1){
                // in range and not at the correct index, swap them
                swap(i, nums[i] - 1, nums);
            }
            else{
                // in range and in the right index
                i++;
            }
        }
        for(i = 0; i < N; i++){
            if(i != nums[i] - 1){
                return i + 1;
            }
        }

        return -1;
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    } 

    public static void main(String[] args) {
        System.out.println(FirstSmallestMissingPositive.findNumber(new int[] { -3, 1, 5, 4, 2 }));
        System.out.println(FirstSmallestMissingPositive.findNumber(new int[] { 3, -2, 0, 1, 2 }));
        System.out.println(FirstSmallestMissingPositive.findNumber(new int[] { 3, 2, 5, 1 }));
    }
    
}
