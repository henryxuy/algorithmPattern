import java.util.*;

public class ReplacingOnes {
    public static int findLength(int[] arr, int k) {
        int start = 0, end = 0;
        int[] countArray = new int[2];
        int N = arr.length;
        int maxLen = 0;
        Arrays.fill(countArray, 0);

        while(end < N){
            countArray[arr[end]]++;
            end++;

            while(countArray[0] > k){
                countArray[arr[start]]--;
                start++;
            }
            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }
    
}
