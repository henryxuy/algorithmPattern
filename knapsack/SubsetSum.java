class SubsetSum {

    public boolean canPartition(int[] num, int sum) {
        // find out whether there is a subset of num whose sum = sum
        int N = num.length;
        Boolean[][] dpTable = new Boolean[N][sum + 1];
        // base case: dpTable[0][...] = true only if num[0] == ...
        // dpTable[...][0] = true
        for(int i = 0; i < N; i++){
            dpTable[i][0] = true;
        }
        for(int j = 1; j <= sum; j++){
            dpTable[0][j] = num[0] == j ? true: false;
        }
        // state transition: dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num[i]] if available
        for(int i = 1; i < N; i++){
            for(int j = 1; j <= sum; j++){
                if(num[i] <= j){
                    dpTable[i][j] = dpTable[i - 1][j] || dpTable[i - 1][j - num[i]];
                }
                else{
                    dpTable[i][j] = dpTable[i - 1][j];
                }
            }
        }

        return dpTable[N - 1][sum];
    }
  
    public static void main(String[] args) {
        SubsetSum ss = new SubsetSum();
        int[] num = { 1, 2, 3, 7 };
        System.out.println(ss.canPartition(num, 6));
        num = new int[] { 1, 2, 7, 1, 5 };
        System.out.println(ss.canPartition(num, 10));
        num = new int[] { 1, 3, 4, 8 };
        System.out.println(ss.canPartition(num, 6));
    }
  }