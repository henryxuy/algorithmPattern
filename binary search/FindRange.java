class FindRange {
    public static int[] findRange(int[] arr, int key) {
        // search for left bound first, and then right
        // O(logN)
        int[] result = new int[] { -1, -1 };
        result[0] = search(arr, key, true);
        if (result[0] != -1) // no need to search, if 'key' is not present in the input array
            result[1] = search(arr, key, false);
        return result;
    }
    
      // modified Binary Search
      private static int search(int[] arr, int key, boolean searchLeft) {
        int keyIndex = -1;
        boolean isFound = false;
        int left = 0, right = arr.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] < key){
                left = mid + 1;
            }
            else if(arr[mid] > key){
                right = mid - 1;
            }
            else{
                isFound = true;
                if(searchLeft){
                    // shrink right to find the left bound
                    right = mid - 1;
                }
                else{
                    // search right
                    left = mid + 1;
                }
            }
        }
        if(isFound){
            if(searchLeft){
                keyIndex = left;
            }
            else{
                keyIndex = right;
            }
        }

        return keyIndex;
    }


    public static int[] findRangeOther(int[] arr, int key) {
        // not good: worst case O(N)
        int[] result = new int[] { -1, -1 };
        int left = 0, right = arr.length - 1;
        int mid;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(arr[mid] == key){
                // expand the interval to find the duplicates
                left = mid;
                right = mid;
                while(left >= 0 && arr[left] == key){
                    left--;
                }
                while(right < arr.length && arr[right] == key){
                    right++;
                }
                result[0] = left + 1;
                result[1] = right - 1;
                return result;
            }
            else if(arr[mid] < key){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        return result;
    }
  
    public static void main(String[] args) {
        int[] result = FindRange.findRange(new int[] { 4, 6, 6, 6, 9 }, 6);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 12);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    }
  }
