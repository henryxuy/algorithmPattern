class Knapsack {
    static int solveKnapsackCompressed(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
          return 0;
    
        int n = profits.length;
        int[] dp = new int[capacity + 1];
    
        // if we have only one weight, we will take it if it is not more than the
        // capacity
        for (int c = 0; c <= capacity; c++) {
          if (weights[0] <= c)
            dp[c] = profits[0];
        }
    
        // process all sub-arrays for all the capacities
        for (int i = 1; i < n; i++) {
          for (int c = capacity; c >= 0; c--) {
            int profit1 = 0, profit2 = 0;
            // include the item, if it is not more than the capacity
            if (weights[i] <= c)
              profit1 = profits[i] + dp[c - weights[i]];
            // exclude the item
            profit2 = dp[c];
            // take maximum
            dp[c] = Math.max(profit1, profit2);
          }
        }
    
        return dp[capacity];
      }


    public int solveKnapsackMemo(int[] profits, int[] weights, int capacity) {
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        return this.knapsackRecursive(dp, profits, weights, capacity, 0);
      }
    
      private int knapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity,
          int currentIndex) {
    
        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
          return 0;
    
        // if we have already solved a similar problem, return the result from memory
        if(dp[currentIndex][capacity] != null)
          return dp[currentIndex][capacity];
    
        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profit1 = 0;
        if( weights[currentIndex] <= capacity )
            profit1 = profits[currentIndex] + knapsackRecursive(dp, profits, weights,
                    capacity - weights[currentIndex], currentIndex + 1);
    
        // recursive call after excluding the element at the currentIndex
        int profit2 = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);
    
        dp[currentIndex][capacity] = Math.max(profit1, profit2);
        return dp[currentIndex][capacity];
      }

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        // dp[i, weightsUsed]: max profit when using items[0..i] and weightsUsed
        // result = dp[n][capacity]
        // base case: dp[0][...] = profits[0], if ... >= weights[0], dp[...][0] = 0
        int numItems = weights.length;
        int[][] dpTable = new int[numItems + 1][capacity + 1];
        // initialization for base cases
        // for(int i = 0; i < numItems; i++){
        //     dpTable[i][0] = 0;
        // }
        // for(int w = 0; w <= capacity; w++){
        //     if(w >= weights[0]){
        //         dpTable[0][w] = profits[0];
        //     }
        // }

        // loop
        for(int i = 1; i <= numItems; i++){
            for(int w = 1; w <= capacity; w++){
                if(w - weights[i - 1] >= 0){
                    // able to sub in the (i - 1)th item
                    dpTable[i][w] = Math.max(dpTable[i - 1][w], dpTable[i - 1][w - weights[i - 1]] + profits[i - 1]);
                }
                else{
                    dpTable[i][w] = dpTable[i - 1][w];
                }
            }
        }

        return dpTable[numItems][capacity];
    }
  
    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);   
    }
}




