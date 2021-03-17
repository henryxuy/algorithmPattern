class MaxSumSubArrayOfSizeK {
    public static int findMaxSumSubArray(int k, int[] arr) {
        // TODO: Write your code here
        int N = arr.length;
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            currentSum += arr[i];
            if(i >= k){
                currentSum -= arr[i - k];
                if(currentSum > maxSum){
                  maxSum = currentSum;
              }
            }
        }
        return maxSum;
      }
  }