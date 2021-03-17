public class RemoveDuplicates {
    public static int remove(int[] arr){
        // in-place move the non duplicated elements in the front of the array
        int nextNonDuplicate = 1;    // track the non-duplicated index where we can move the element to nextNonDuplicate - 1
        int N = arr.length;
        for(int i = 1; i < N; i++){
            if(arr[nextNonDuplicate - 1] != arr[i]){
                // not same, non duplicate
                arr[nextNonDuplicate] = arr[i];
                nextNonDuplicate++;
            }
        }

        return nextNonDuplicate;
    }
    
}
