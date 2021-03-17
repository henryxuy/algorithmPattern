import java.util.*;

public class SubarrayProductLessThanK {
    // public static List<List<Integer>> findSubarrays(int[] arr, int target){
    //     // find all contiguous(!!) subarrays whose product is < target
    //     // length is not determined
    //     List<List<Integer>> subarrays = new ArrayList<>();
    //     int N = arr.length;
    //     // TODO: Write your code here
    //     for(int length = 1; length < N; length++){
    //         boolean found = false;      // initialize the tracker
    //         for(int start = 0; start < N - length + 1; start++){
    //             int tempProduct = 1;
    //             List<Integer> tempList = new ArrayList<>();
    //             for(int i = start; i < start + length; i++){
    //                 // calculate the product of arr[start...start + length - 1]
    //                 tempProduct = tempProduct * arr[i];
    //                 tempList.add(arr[i]);
    //             }
    //             if(tempProduct < target){
    //                 // if meets the requirement
    //                 subarrays.add(tempList);
    //                 found = true;
    //             }
    //         }
    //         if(!found){
    //             return subarrays;
    //         }
    //     }

    //     return subarrays;
    // }

    public static List<List<Integer>> findSubarrays(int[] arr, int target){
        // find all contiguous(!!) subarrays whose product is < target
        // sliding window + two pointers
        List<List<Integer>> subarrays = new ArrayList<>();
        int N = arr.length;
        int tempProduct = 1;
        int right = 0, left = 0;
        for(; right < N; right++){
            // expand the window
            tempProduct *= arr[right];

            // shrink the window to meet the requirement
            while(tempProduct >= target && left < right){
                tempProduct /= arr[left];
                left++;
            }
            // add the results (arr[left..right], arr[left+1..right], ...)
            List<Integer> tempResultList = new LinkedList<>();
            for(int i = right; i >= left; i--){
                tempResultList.add(0, arr[i]);      // add the arr[i] in front of the result list from right to left
                subarrays.add(new ArrayList<>(tempResultList));     // add multiple results
            }
        }

        return subarrays;
    }


    
}
