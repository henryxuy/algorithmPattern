public class RotationCountOfRotatedArray {
    public static int countRotations(int[] arr) {
        int N = arr.length;
        if(N == 0){
            // undefined result
            return -1;
        }
        // edge cases
        if(arr[0] > arr[1]){
            return 1;
        }


        int left = 0, right = N - 1;
        while(left <= right){
            int mid = (left + right) / 2;

            // avoid boundary issue
            if (mid < right && arr[mid] > arr[mid + 1]) // if mid is greater than the next element
                return mid + 1;
            if (mid > left && arr[mid - 1] > arr[mid]) // if mid is smaller than the previous element
                return mid;

            // if(arr[mid] <= arr[mid - 1] && arr[mid] <= arr[mid + 1]){
            //     // found the point, this method has boundary issue
            //     return mid;
            // }

            // compare the arr[left] with arr[mid] to determine which part we are in
            if(arr[left] <= arr[mid]){
                // we are in the first half. arr[left...mid] are ascending
                left = mid + 1;
            }
            else{
                // arr[left...mid] are not ascending, and then arr[mid + 1...right] are ascending
                right = mid - 1;
            }
        }

        return 0; 
    }
    

    public static void main(String[] args) {
        System.out.println(RotationCountOfRotatedArray.countRotations(new int[] { 10, 15, 1, 3, 8 }));
        System.out.println(RotationCountOfRotatedArray.countRotations(new int[] { 4, 5, 7, 9, 10, -1, 2 }));
        System.out.println(RotationCountOfRotatedArray.countRotations(new int[] { 1, 3, 8, 10 }));
        System.out.println(RotationCountOfRotatedArray.countRotations(new int[] { 3, 8, 10, 1 }));
    }
}
