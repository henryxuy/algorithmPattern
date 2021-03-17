import java.util.*;

public class TripletSumCloseToTarget {
    // public static int searchTriplet(int[] arr, int targetSum){
    //     if (arr == null || arr.length < 3)
    //         throw new IllegalArgumentException();
    //     Arrays.sort(arr);
    //     // find a triplet closest to the targetSum. Return the sum of the triplet
    //     // if there are multiple triplets, return the one with the smallest sum
    //     int N = arr.length;
    //     int smallestDist = Integer.MAX_VALUE;
    //     int resultSum = Integer.MAX_VALUE;
    //     // 3 sum = loop + 2sum. Leave 2-element space.
    //     for(int i = 0; i < N - 2; i++){
    //         int left = i + 1, right = N - 1;
    //         while(left < right){
    //             int tempSum = arr[i] + arr[left] + arr[right];
    //             int tempDist = Math.abs(tempSum - targetSum);
    //             boolean isLarger = (tempSum >= targetSum) ? true : false;
    //             if(isLarger){
    //                 // make the sum smaller
    //                 right--;
    //             }
    //             else{
    //                 left++;
    //             }

    //             if(tempDist < smallestDist){
    //                 smallestDist = tempDist;
    //                 resultSum = tempSum;
    //             }
    //             else if(tempDist == smallestDist){
    //                 resultSum = Math.min(resultSum, tempSum);
    //             }
    //             else{
    //                 // tempDist > smallestDist
    //             }
    //         }
    //     }
    //     return resultSum;
    // }

    public static int searchTriplet(int[] arr, int targetSum) {
        if (arr == null || arr.length < 3)
          throw new IllegalArgumentException();
    
        Arrays.sort(arr);
        int smallestDifference = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
          int left = i + 1, right = arr.length - 1;
          while (left < right) {
            // comparing the sum of three numbers to the 'targetSum' can cause overflow
            // so, we will try to find a target difference
            int targetDiff = targetSum - arr[i] - arr[left] - arr[right];
            if (targetDiff == 0) //  we've found a triplet with an exact sum
              return targetSum - targetDiff; // return sum of all the numbers
    
            // the second part of the above 'if' is to handle the smallest sum when we have more than one solution
            // small sum, small arr[i] + arr[left] + arr[right], meaning the large targetDiff = target - ...
            if (Math.abs(targetDiff) < Math.abs(smallestDifference)
                || (Math.abs(targetDiff) == Math.abs(smallestDifference) && targetDiff > smallestDifference))
              smallestDifference = targetDiff; // save the closest and the biggest difference  
    
            if (targetDiff > 0)
              left++; // we need a triplet with a bigger sum
            else
              right--; // we need a triplet with a smaller sum
          }
        }
        return targetSum - smallestDifference;
      }




    
}
