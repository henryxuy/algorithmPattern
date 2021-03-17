class CeilingOfANumber {

    public static int searchCeilingOfANumber(int[] arr, int key) {
        // find the ceiling number of key in array (ascending).
        // if not found, return -1
        if(key > arr[arr.length - 1]){
            return -1;
        }

        int left = 0, right = arr.length - 1;
        // use binary search to find the first element >= key
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] == key){
                return mid;
            }
            else if(arr[mid] < key){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        // since the loop is running until 'start <= end', so at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array, so the next big number will be arr[start]

        return left;
    }
  
    public static void main(String[] args) {
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] { 4, 6, 10 }, 6));
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] { 4, 6, 10 }, 17));
        System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] { 4, 6, 10 }, -1));
    }
  }