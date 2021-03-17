class MinimumDifference {

    public static int searchMinDiffElement(int[] arr, int key) {
        if(key < arr[0]){
            return arr[0];
        }
        if(key > arr[arr.length - 1]){
            return arr[arr.length - 1];
        }
        // find the element with the minimum difference with the given key
        int left = 0, right = arr.length - 1;
        int mid = 0;
        System.out.println(left);
        System.out.println(right);
        while(left <= right){
            mid = left + (right - left) / 2;
            if(arr[mid] == key){
                return arr[mid];
            }
            else if(arr[mid] < key){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        // int distance = Integer.MAX_VALUE;
        // int result = 0;
        // if(mid >= 0 && mid < arr.length - 1){
        //     distance = Math.abs(arr[mid] - key);
        //     result = arr[mid];
        // }
        // if(mid - 1 >= 0 && mid - 1 < arr.length && Math.abs(arr[mid - 1] - key) < distance){
        //     distance = Math.abs(arr[mid - 1] - key);
        //     result = arr[mid - 1];
        // }
        // if(mid + 1 >= 0 && mid + 1 < arr.length && Math.abs(arr[mid + 1] - key) < distance){
        //     distance = Math.abs(arr[mid + 1] - key);
        //     result = arr[mid + 1];
        // }
        // return result;

        // at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array
        // return the element which is closest to the 'key'


        if((arr[left] - key) < (key - arr[right])){
            return arr[left];
        }

        return arr[right];
    }
  
    public static void main(String[] args) {
        // System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 4, 6, 10 }, 7));
        // System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 4, 6, 10 }, 4));
        // System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 4, 6, 10 }, 17));
    }
  }