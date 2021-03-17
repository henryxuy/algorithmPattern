public class SearchRotatedArray {
    public static int search(int[] arr, int key) {
        int N = arr.length;
        int start = 0, end = N - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] == key){
                return mid;
            }

            // the only difference from the previous solution,
            // if numbers at indexes start, mid, and end are same, we can't choose a side
            // the best we can do, is to skip one number from both ends as key != arr[mid]
            if ((arr[start] == arr[mid]) && (arr[end] == arr[mid])) {
                ++start;
                --end;
            }
            else if(arr[start] <= arr[mid]){
                // arr[start...middle] is increasing
                if(key >= arr[start] && key < arr[mid]){
                    // key < arr[mid]
                    end = mid - 1;
                }
                else{
                    // key > arr[mid] || key < arr[start]
                    start = mid + 1;
                }
            }
            else{
                // arr[middle + 1...end] is increasing
                if(key > arr[mid] && key <= arr[end]){
                    // key > arr[mid], search in the latter
                    start = mid + 1;
                }
                else{
                    // key < arr[mid] || key > arr[end], search in the front
                    end = mid - 1;
                }
            }
        }

        return -1;
    }
    
      public static void main(String[] args) {
        System.out.println(SearchRotatedArray.search(new int[] { 10, 15, 1, 3, 8 }, 15));
        System.out.println(SearchRotatedArray.search(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));
    }
}
