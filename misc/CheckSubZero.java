import java.util.*;

public class CheckSubZero {

    public static boolean findSubZero(int[] arr) {
        // returns true if a contiguous subarray exists with a sum = 0
        // second solution: hashMap to speed up preSum
        int start = 0, end = 0;
        int N = arr.length;
        int sum = 0;
        HashMap<Integer, Integer> mapSumIndex = new HashMap<>();

        for(int i = 0; i < N; i++){
            sum += arr[i];      // keep the sum, similar to preSum

            if(arr[i] == 0 || sum == 0 || mapSumIndex.containsKey(sum)){
                // mapSumIndex.containsKey(sum) is the same as preSum[j] - preSum[i] == 0
                return true;
            }

            mapSumIndex.put(sum, i);
        }

        
        return false;
    }
    
    public static boolean findSubZeroPrefixSum(int[] arr){
        // first solution: prefix sum
        int N = arr.length;
        // preSum[i + 1] = preSum[i] + array[i]
        int[] preSum = new int[N + 1];
        preSum[0] = 0;
        for(int i = 0; i < N; i++){
            preSum[i + 1] = preSum[i] + arr[i];
            for(int j = -1; j < i; j++){
                if(preSum[i + 1] - preSum[j + 1] == 0){
                    return true;
                }
            }
        }
        // // search continguous subarray sum = 0
        // for(int j = 1; j <= N; j++){
        //     for(int i = 0; i < j; i++){
        //         if(preSum[j] - preSum[i] == 0){
        //             return true;
        //         }
        //     }
        // }

        return false;
    }

}
