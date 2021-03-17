public class SearchBitonicArray {
    public static int search(int[] arr, int key) {
        int N = arr.length;
        if(N == 1){
            return arr[0] == key ? 0: -1;
        }
        // search the peak first
        int peakIndex = -1;
        int left = 0, right = N - 1;
        if(arr[0] >= arr[1]){
            peakIndex = 0;
        }
        else if(arr[N - 1] >= arr[N - 2]){
            peakIndex = N - 1;
        }
        else{
            while(left <= right){
                int mid = (left + right) / 2;
                if(arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1]){
                    peakIndex = mid;
                    break;
                }
                else if(arr[mid] <= arr[mid + 1]){
                    left = mid;
                }
                else{
                    right = mid;
                }
            }
        }


        // search in [0...peakIndex] (increasing) and [peakIndex...N-1] (decreasing) separately
        left = 0; 
        right = peakIndex;
        while(left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] == key){
                return mid;
            }
            else if(arr[mid] > key){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        left = peakIndex + 1;
        right = N - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] == key){
                return mid;
            }
            else if(arr[mid] > key){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }


        return -1;
    }

    public static void main(String[] args) {
        System.out.println(SearchBitonicArray.search(new int[] { 1, 3, 8, 4, 3 }, 4));
        System.out.println(SearchBitonicArray.search(new int[] { 3, 8, 3, 1 }, 8));
        System.out.println(SearchBitonicArray.search(new int[] { 1, 3, 8, 12 }, 12));
        System.out.println(SearchBitonicArray.search(new int[] { 10, 9, 8 }, 10));
    }

    
}
