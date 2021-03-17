import java.util.*;

public class TripletWithSmallerSum {
    public static int searchTriplets(int[] arr, int target) {
        // count all triplets that has sum < target
        int count = 0;
        int N = arr.length;
        // TODO: Write your code here
        Arrays.sort(arr);
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1, right = N - 1;
            while(left < right){
                if(arr[i] + arr[left] + arr[right] < target){
                    // found the triplet
                    // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between 
                    // left and right to get a sum less than the target sum
                    count += right - left;      // key code
                    left++;
                }
                else{
                    right--;
                }
            }
        }

        return count;
    }

    
}
