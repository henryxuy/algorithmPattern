public class MinSizeSubArraySum {
    public static int findMinSubArray(int S, int[] arr) {
        // TODO: Write your code here
        int N = arr.length;
        int minInterval = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0, j = 0;

        while(j < N){
            sum += arr[j];
            j++;

            while(sum >= S){
                if((j - i) < minInterval){
                    minInterval = j - i;
                }
                sum -= arr[i];
                i++;
            }
        }


        return minInterval;
    }
    
}
