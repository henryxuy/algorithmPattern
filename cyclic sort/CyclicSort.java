class CyclicSort {
    // Cycle sort is an in-place sorting Algorithm, unstable sorting algorithm
    // time O(n), space O(1)
    public static void sort(int[] nums) {
        int N = nums.length;
        int i = 0;
        while(i < N) {
            int j = nums[i] - 1;      // j: the location of nums[i] should be
            if(nums[i] != nums[j]){
                swap(i, j, nums);     // if not correct, swap nums[i] to nums[i] - 1
            }
            else{
                i++;                // already in the right place, go ahead
            }
        }
        // time complexity: at most N swaps, since each swap makes one element in the right position.

    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

  }