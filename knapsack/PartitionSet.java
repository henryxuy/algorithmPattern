class PartitionSet {

    static boolean canPartition(int[] num){
        int sum = 0;
        int N = num.length;
        for(int n: num){
            sum += n;
        }
        if(sum % 2 != 0){
            return false;
        }

        // bottom-up dp, state: (currentIndex, targetSum). true if we can make targetSum from prefix[0...currentIndex] 
        Boolean[][] dpTable = new Boolean[N][sum / 2 + 1];
        // base case: dp[...][0] = true, dp[0][...] = true if num[0] == ...
        for(int i = 0; i < N; i++){
            dpTable[i][0] = true;
        }
        for(int j = 1; j <= sum / 2; j++){
            if(num[0] == j){
                dpTable[0][j] = true;
            }
            else{
                dpTable[0][j] = false;
            }
        }
        // result: dp[N - 1][sum / 2]
        // loop direction: outer: currentIndex, inner: targetSum
        for(int currentIndex = 1; currentIndex < N; currentIndex++){
            for(int targetSum = 1; targetSum <= sum / 2; targetSum++){
                if(num[currentIndex] > targetSum){
                    dpTable[currentIndex][targetSum] = dpTable[currentIndex - 1][targetSum];
                }
                else{
                    dpTable[currentIndex][targetSum] = dpTable[currentIndex - 1][targetSum] || dpTable[currentIndex - 1][targetSum - num[currentIndex]];
                }
            }
        }

        return dpTable[N - 1][sum / 2];
    }



    private static boolean canPartitionMemoHelper(int[] num, Boolean[][] memo, int currentIndex, int targetSum){
        // exit condition
        if(targetSum == 0){
            return true;
        }
        if(num.length == 0 || currentIndex >= num.length){
            return false;
        }
    
        // memo[currentIndex][targetSum]: true or false
        if(memo[currentIndex][targetSum] != null){
            return memo[currentIndex][targetSum];
        }
        
        if(num[currentIndex] <= targetSum){
            memo[currentIndex][targetSum] = canPartitionMemoHelper(num, memo, currentIndex + 1, targetSum - num[currentIndex]) || canPartitionMemoHelper(num, memo, currentIndex + 1, targetSum);
        }
        else{
            memo[currentIndex][targetSum] = canPartitionMemoHelper(num, memo, currentIndex + 1, targetSum);
        }

        return memo[currentIndex][targetSum];
    }



    static boolean canPartitionMemo(int[] num) {
        int sum = 0;
        int N = num.length;
        for(int i = 0; i < N; i++){
            sum += num[i];
        }

        if(sum % 2 != 0){
            return false;
        }
        // state: currentIndex, remaining target sum
        // initialize to null
        Boolean[][] memo = new Boolean[N][sum / 2 + 1];

        return canPartitionMemoHelper(num, memo, sum / 2, 0);
    }

    static boolean canPartitionBruteForce(int[] num) {
        int sum = 0;
        int N = num.length;
        for(int i = 0; i < N; i++){
            sum += num[i];
        }

        if(sum % 2 != 0){
            return false;
        }

        return canPartitionRecursive(num, sum / 2, 0);
    }

    private static boolean canPartitionRecursive(int[] num, int sum, int currentIndex) {
        // find a set sum = sumTotal / 2
        if(sum == 0){
            return true;
        }
        if(num.length == 0 || currentIndex >= num.length){
            return false;
        }
        // recursive call after choosing the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        if(num[currentIndex] <= sum){
            // can add. use or
            return canPartitionRecursive(num, sum - num[currentIndex], currentIndex + 1) || canPartitionRecursive(num, sum, currentIndex + 1);
        }
        else{
            // cannot add
            return canPartitionRecursive(num, sum, currentIndex + 1);
        }
    }




  }