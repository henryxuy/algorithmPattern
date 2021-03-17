public class SortedArraySquares {
    public static int[] makeSquares(int[] arr) {
        int[] squares = new int[arr.length];
        // TODO: Write your code here
        // find the point closest to 0, then the square increases in both left and right
        // just compare left and right, and add the smaller to the front of the array
        // another way: start at two sides, and add the larger to the end of the array 
        int N = arr.length;
        int left = 0, right = 0;
        int middle = 0;
        int startIndex = 0;
        for(; middle < N; middle++){
            // middle(right) is the first element > 0
            if(arr[middle] > 0){
                left = middle - 1;
                right = middle;
                break;
            }
        }
        while(left >= 0 && right < N){
            int leftAbs = -arr[left];
            int rightAbs = arr[right];
            if(leftAbs <= rightAbs){
                squares[startIndex++] = leftAbs * leftAbs;
                left--;
            }
            else{
                squares[startIndex++] = rightAbs * rightAbs;
                right++;
            }
        }
        // add back the remaining elements
        while(left >= 0){
            int leftAbs = -arr[left];
            squares[startIndex++] = leftAbs * leftAbs;
            left--;
        }
        while(right < N){
            int rightAbs = arr[right];
            squares[startIndex++] = rightAbs * rightAbs;
            right++;
        }


        // int left = 0, right = N - 1;
        // int endIndex = N - 1;
        // while(left <= right){
        //     int leftSquared = arr[left] * arr[left];
        //     int rightSquared = arr[right] * arr[right];
        //     if(leftSquared >= rightSquared){
        //         squares[endIndex--] = leftSquared;
        //         left++;
        //     }
        //     else{
        //         squares[endIndex--] = rightSquared;
        //         right--;
        //     }
        // }

        return squares;
    }
    
}
