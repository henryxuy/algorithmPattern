public class SubsetSumCount {

    static int countSubsets(int[] num, int sum) {
        // dp[i][s]: using prefix[...i-1], the count of sum = s
        // base case: dp[0, 0] = 1, dp[0, ...] = 0, dp[i, 0] = 1
        // result: dp[N][sum]
        int N = num.length;
        int[][] dpTable = new int[N + 1][sum + 1];
        dpTable[0][0] = 1;
        for(int j = 1; j <= sum; j++){
            dpTable[0][j] = 0;
        }
        for(int i = 1; i <= N; i++){
            dpTable[i][0] = 1;
        }

        // state transition: dp[i + 1, s] = dp[i, s - nums[i]] + dp[i, s]
        // loop direction: outer i, inner s
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= sum; j++){
                dpTable[i][j] = dpTable[i - 1][j];
                if(j - num[i - 1] >= 0){
                    dpTable[i][j] += dpTable[i - 1][j - num[i - 1]];
                }
            }
        }

        return dpTable[N][sum];
    }

    public static void main(String[] args) {
        SubsetSumCount ss = new SubsetSumCount();
        int[] num = { 1, 1, 2, 3 };
        System.out.println(ss.countSubsets(num, 4));
        num = new int[] { 1, 2, 7, 1, 5 };
        System.out.println(ss.countSubsets(num, 9));
    }
    
}
