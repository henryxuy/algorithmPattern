public class FindValley {

    public static int FindValley(int[] arr){
        // find the index of the valley point

        // edge cases
        int N = arr.length;
        if(N == 1){
            return 0;
        }
        if(arr[0] >= arr[1]){
            return 0;
        }
        if(arr[N - 2] <= arr[N - 1]){
            return N - 1;
        }

        int left = 0, right = N - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] <= arr[mid - 1] && arr[mid] <= arr[mid + 1]){
                // find the valley point 
                return mid;
            }
            else if(arr[mid] <= arr[mid + 1]){
                right = mid;
            }
            else{
                left = mid;
            }
        }

        return -1;
    }
    
}
