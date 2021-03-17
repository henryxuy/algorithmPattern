class PartitionSetDifference {

    public int canPartitionBottomUp(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
          sum += num[i];
    
        int n = num.length;
        boolean[][] dp = new boolean[n][sum/2 + 1];
    
        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for(int i=0; i < n; i++)
          dp[i][0] = true;
    
        // with only one number, we can form a subset only when the required sum is equal to that number
        for(int s=1; s <= sum/2 ; s++) {
          dp[0][s] = (num[0] == s ? true : false);
        }
    
        // process all subsets for all sums
        for(int i=1; i < num.length; i++) {
          for(int s=1; s <= sum/2; s++) {
            // if we can get the sum 's' without the number at index 'i'
            if(dp[i-1][s]) {
              dp[i][s] = dp[i-1][s];
            } else if (s >= num[i]) {
              // else include the number and see if we can find a subset to get the remaining sum
              dp[i][s] = dp[i-1][s-num[i]];
            }
          }
        }
    
        int sum1 = 0;
        // Find the largest index in the last row which is true
        for (int i = sum / 2; i >= 0; i--) {
          if (dp[n-1][i] == true) {
              sum1 = i;
              break;
          }
        }
    
        int sum2 = sum - sum1;
        return Math.abs(sum2-sum1);
      }


    public int canPartitionTopDown(int[] num){
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            sum += num[i];

        Integer[][] dp = new Integer[num.length][sum + 1];
        return canPartitionRecursive(dp, num, 0, 0, 0);
    }

    private int canPartitionRecursive(Integer[][] dp, int[] num, int currentIndex, int sum1, int sum2){
        // base case
        if(currentIndex == num.length){
            return Math.abs(sum1 - sum2);
        }

        // state: currentIndex, sum1 (sum2 = sum - sum1)
        // memorization here
        if(dp[currentIndex][sum1] != null){
            return dp[currentIndex][sum1];
        }
        // include the number in first set
        int diff1 = canPartitionRecursive(dp, num, currentIndex + 1, sum1 + num[currentIndex], sum2);
        // include the number in the second set
        int diff2 = canPartitionRecursive(dp, num, currentIndex + 1, sum1, sum2 + num[currentIndex]);
        dp[currentIndex][sum1] = Math.min(diff1, diff2);

        return dp[currentIndex][sum1];
    }



    public int canPartition(int[] num) {
        // partition the num into two subsets with the minimum difference between their sums
        int N = num.length;
        int sum = 0;
        for(int n: num){
            sum += n;
        }
        // dp[i][j]: in prefix num[0...i], the smallest difference between sum of possible set and j
        // we make the value in dp >= 0, i.e. we are picking the smaller set
        // Note that two sets are symmetric around sum / 2
        // final result: 2 * (dp[N - 1][sum / 2]) 
        if(sum % 2 != 0){
            // odd case
            int[][] dpTable = new int[N][sum / 2 + 2];
            // base case: dpTable[...][0] = 0, dpTable[0][x] = Math.min(Math.max(x - num[0], 0), x) = x - num[0] if x >= num[0] else x
            for(int i = 0; i < N; i++){
                dpTable[i][0] = 0;
            }
            for(int x = 0; x <= sum / 2 + 1; x++){
                dpTable[0][x] = x >= num[0] ? x - num[0] : x;
            }
            // state transition: dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - num[i]]) (if j - num[i] >= 0)
            // else dp[i-1][j]
            for(int i = 1; i < N; i++){
                for(int j = 0; j <= sum / 2 + 1; j++){
                    if(j - num[i] >= 0){
                        // may include num[i]
                        dpTable[i][j] = Math.min(dpTable[i - 1][j], dpTable[i - 1][j - num[i]]);
                    }
                    else{
                        // not include num[i]
                        dpTable[i][j] = dpTable[i - 1][j];
                    }
                }
            }
            int tempDiff = dpTable[N - 1][sum / 2 + 1];
            int smallSum = sum / 2 + 1 - tempDiff;

            return sum - smallSum - smallSum;
        }  
        else{
            // even case
            int[][] dpTable = new int[N][sum / 2 + 1];
            for(int i = 0; i < N; i++){
                dpTable[i][0] = 0;
            }
            for(int x = 0; x <= sum / 2; x++){
                dpTable[0][x] = x >= num[0] ? x - num[0] : x;
            }
            for(int i = 1; i < N; i++){
                for(int j = 0; j <= sum / 2; j++){
                    if(j - num[i] >= 0){
                        // may include num[i]
                        dpTable[i][j] = Math.min(dpTable[i - 1][j], dpTable[i - 1][j - num[i]]);
                    }
                    else{
                        // not include num[i]
                        dpTable[i][j] = dpTable[i - 1][j];
                    }
                }
            }
            int smallSum = sum / 2 - dpTable[N - 1][sum / 2];

            return sum - smallSum * 2;
        } 
    }
  
    public static void main(String[] args) {
        PartitionSetDifference ps = new PartitionSetDifference();
        int[] num = {1, 2, 3, 9};
        System.out.println(ps.canPartitionTopDown(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ps.canPartitionTopDown(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(ps.canPartitionTopDown(num));
    }
  }