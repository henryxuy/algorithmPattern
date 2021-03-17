import java.util.*;

class PairWithTargetSum{
    public static int[] search(int[] arr, int targetSum){
        // sorted array, two sum
        int left = 0, right = arr.length - 1;

        while(right > left){
            int tempSum = arr[left] + arr[right];
            if(tempSum == targetSum){
                return new int[] {left, right};
            }
            else if(tempSum < targetSum){
                left++;
            }
            else{
                right--;
            }
        }

        return new int[] { -1, -1 };
    }

    public static int[] searchHashMap(int[] arr, int targetSum) {
        HashMap<Integer, Integer> nums = new HashMap<>(); // to store numbers and their indices
        for (int i = 0; i < arr.length; i++) {
          if (nums.containsKey(targetSum - arr[i]))
            return new int[] { nums.get(targetSum - arr[i]), i };
          else
            nums.put(arr[i], i); // put the number and its index in the map
        }
        return new int[] { -1, -1 }; // pair not found
      }
    
      public static void main(String[] args) {
        int[] result = PairWithTargetSum.search(new int[] { 1, 2, 3, 4, 6 }, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = PairWithTargetSum.search(new int[] { 2, 5, 9, 11 }, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
      }
}


