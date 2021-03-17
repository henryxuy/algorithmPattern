public class DutchFlag {
    public static void sortCyclic(int[] arr){
        // cyclic sort: track the last loc. of 0 in the first loop
        // and track the last loc. of 1 in the second loop
        int lastIndexZero = 0;
        int N = arr.length;
        for(int i = 0; i < N; i++){
            if(arr[i] == 0 && i != lastIndexZero){
                swap(arr, i, lastIndexZero);
                lastIndexZero++;
            }
        }
        if(lastIndexZero == N - 1){
            return;
        }
        int lastIndexOne = lastIndexZero;
        for(int i = lastIndexOne; i < N; i++){
            if(arr[i] == 1 && i != lastIndexOne){
                swap(arr, i, lastIndexOne);
                lastIndexOne++;
            }
        }

    }


    public static void sort(int[] arr) {
        // two pointers
        int N = arr.length;
        int left = 0, right = N - 1;
        for(int i = 0; i <= right;){
            // move all 0's before left, and all 2's after right
            if(arr[i] == 0){
                swap(arr, i, left);
                left++;
                i++;
            }
            else if(arr[i] == 2){
                swap(arr, i, right);
                right--;
            }
            else{
                // arr[i] == 1
                i++;
            }
        }

    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}
