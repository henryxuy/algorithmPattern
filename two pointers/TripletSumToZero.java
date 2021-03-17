import java.util.*;

public class TripletSumToZero {
    public static List<List<Integer>> searchTriplets(int[] arr) {
        // 3sum, utuilize montonity
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();
        // TODO: Write your code here
        // find all unique triplets that add up to 0
        int N = arr.length;

        // 3sum = loop + 2sum. Note that we need at least 2 elements
        for(int i = 0; i < N - 2; i++){
            if(i > 0 && arr[i] == arr[i - 1]){
                // skip the same elements
                continue;
            }
            addTriplets(i + 1, arr, -arr[i], triplets);
        }

        return triplets;
    }

    private static void addTriplets(int left, int[] arr, int targetSum, List<List<Integer>> tripleList){
        // search arr[left...N], for two sum = targetSum
        int right = arr.length - 1;
        while(left < right){
            int tempSum = arr[left] + arr[right];
            if(tempSum < targetSum){
                left++;
            }
            else if(tempSum > targetSum){
                right--;
            }
            else{
                // find the result
                ArrayList<Integer> resultList = new ArrayList<Integer>();
                resultList.add(-targetSum);
                resultList.add(arr[left]);
                resultList.add(arr[right]);
                tripleList.add(resultList);

                left++;
                right--;
                // skip all duplicate elements, and search the next ones
                while(left < right && arr[left] == arr[left - 1]){
                    left++;
                }

                while(left < right && arr[right] == arr[right + 1]){
                    right--;
                }
            }
        }
    }


    // private List<Integer> pairSumK(int[] arr, int ignoreIndex, int k){
    //     // find the element sums to k (ignoring arr[ignoreIndex])
    //     Map<Integer, Integer> mapNumberTarget = new HashMap<>();
    //     int N = arr.length;
    //     for(int i = 0; i < N; i++){
    //         if(i == ignoreIndex){
    //             continue;
    //         }
    //         if(mapNumberTarget.containsKey(arr[i])){

    //         }
    //         else{
    //             mapNumberTarget.put(arr[i], k - arr[i]);
    //         }

    //     }


    // }
    
}
