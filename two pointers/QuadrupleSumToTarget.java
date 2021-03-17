import java.util.*;

public class QuadrupleSumToTarget {
    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        // 4-sum: sort, fix the first, fix the second, and then use two-pointer 2-sum
        List<List<Integer>> quadruplets = new ArrayList<>();
        int N = arr.length;
        Arrays.sort(arr);
        for(int firstIndex = 0; firstIndex < N - 3; firstIndex++){
            // fix one number
            if(firstIndex > 0 && arr[firstIndex] == arr[firstIndex - 1]){
                // skip same elements to avoid duplicated elements (already considered)
                continue;
            }

            for(int secondIndex = firstIndex + 1; secondIndex < N - 2; secondIndex++){
                if(secondIndex > firstIndex + 1 && arr[secondIndex] == arr[secondIndex - 1]){
                    // skip same elements to avoid duplicated elements (already considered)
                    continue;
                }
                int tempTarget = target - arr[firstIndex] - arr[secondIndex];
                // search 2-sum = tempTarget in [secondIndex + 1...end]
                int left = secondIndex + 1, right = N - 1;
                while(left < right){
                    int tempSum = arr[left] + arr[right];
                    if(tempSum == tempTarget){
                        // List<Integer> tempResult = new ArrayList<>();
                        // tempResult.add(arr[firstIndex]);
                        // tempResult.add(arr[secondIndex]);
                        // tempResult.add(arr[left]);
                        // tempResult.add(arr[right]);
                        quadruplets.add(Arrays.asList(arr[firstIndex], arr[secondIndex], arr[left], arr[right]));
                        left++;
                        right--;
                        // move the indices to avoid duplicated results
                        while(left < right && arr[left] == arr[left - 1]){
                            left++;
                        }
                        while(left < right && arr[right] == arr[right + 1]){
                            right--;
                        }
                    }
                    else if(tempSum < tempTarget){
                        // small, should make it larger
                        left++;
                    }
                    else{
                        // large, should make it smaller
                        right--;
                    }
                }
            }
        }

        return quadruplets;
    }

    public static void main(String[] args) {
        System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[] { 4, 1, 2, -1, 1, -3 }, 1));
        System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[] { 2, 0, -1, 1, -2, 2 }, 2));
    }
    
}
