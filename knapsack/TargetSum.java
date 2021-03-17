class TargetSum {

    public int findTargetSubsets(int[] num, int s) {
        int sumOfArray = 0;
        int N = num.length;
        for(int i = 0; i < N; i++){
            sumOfArray += num[i];
        }
        if(sumOfArray < s || (s + sumOfArray) % 2 != 0){
            return 0;
        }
        int targetSum = (s + sumOfArray) / 2;

        int[][] dpTable = new int[N + 1][targetSum + 1];
        dpTable[0][0] = 1;
        for(int j = 1; j <= targetSum; j++){
            dpTable[0][j] = 0;
        }
        for(int i = 1; i <= N; i++){
            dpTable[i][0] = 1;
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= targetSum; j++){
                dpTable[i][j] = dpTable[i - 1][j];
                if(j - num[i - 1] >= 0){
                    dpTable[i][j] += dpTable[i - 1][j - num[i - 1]];
                }
            }
        }
        

        return dpTable[N][targetSum];
    }
    
    public static void main(String[] args) {
        TargetSum ts = new TargetSum();
        int[] num = {1, 1, 2, 3};
        System.out.println(ts.findTargetSubsets(num, 1));
        num = new int[]{1, 2, 7, 1};
        System.out.println(ts.findTargetSubsets(num, 9));
    }
  }