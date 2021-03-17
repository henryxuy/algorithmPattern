class FindPeak{

    public static int findPeak(int[] arr){
        // return the peak index
        int N = arr.length;

        // edge cases
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
            if((arr[mid - 1] <= arr[mid]) && (arr[mid + 1] <= arr[mid])){
                return mid;
            }
            else if(arr[mid] <= arr[mid + 1]){
                left = mid;
            }
            else{
                right = mid;
            }
        }


        return -1;
    }

    // Driver method
    public static void main(String[] args)
    {
        int[] arr = {7, 20, 4, 1, 0 };
        int[] arr2 = { 20, 4, 1, 0 };
        int[] arr3 = { 1, 3, 20 };
        int[] arr4 = {7, 9, 13, 20, 4};
        // int n = arr.length;
        System.out.println(
            "Index of a peak point is " + findPeak(arr));
        System.out.println(
        "Index of a peak point is " + findPeak(arr2));
        System.out.println(
        "Index of a peak point is " + findPeak(arr3));
        System.out.println(
        "Index of a peak point is " + findPeak(arr4));
    }


}