class MaxInBitonicArray {

    public static int findMax(int[] arr) {
        // find the valley point using binary search.
        // arr first increases, and then decreases
        int left = 0, rignt = arr.length - 1;
        while(left < rignt){
            int mid = left + (rignt - left) / 2;
            if(arr[mid] > arr[mid + 1]){
                // in the descending part
                rignt = mid;
            }
            else if(arr[mid] < arr[mid + 1]){
                // in the ascending part
                left = mid + 1;
            }
        }
        // exit loop: left = right

        return arr[left];
    }
  
    public static void main(String[] args) {
        System.out.println(MaxInBitonicArray.findMax(new int[] { 1, 3, 8, 12, 4, 2 }));
        System.out.println(MaxInBitonicArray.findMax(new int[] { 3, 8, 3, 1 }));
        System.out.println(MaxInBitonicArray.findMax(new int[] { 1, 3, 8, 12 }));
        System.out.println(MaxInBitonicArray.findMax(new int[] { 10, 9, 8 }));
    }
  }