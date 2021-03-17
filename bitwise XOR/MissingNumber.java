class MissingNumber {

    public static int cyclicSort(int[] arr){
        // use cyclic sort to find
        // range 1 - n, n - 1 numbers, find the missing number
        int i = 0;
        int N = arr.length;
        while(i < N){
            int j = arr[i] - 1;       // the location of nums[i] should be nums[i] - 1
            if(j < N && arr[i] != arr[j]){
                swap(arr, i, j);
            }
            else{
                i++;
            }
        }
        for(int j = 0; j < N; j++){
            if(arr[j] != j + 1){
                // j + 1 is missing
                return j + 1;
            }
        }

        return arr.length;
    }


    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static int findMissingNumber(int[] arr) {
        // int n = arr.length + 1;
        // // find sum of all numbers from 1 to n.
        // int s1 = 0;
        // for (int i = 1; i <= n; i++)
        //     s1 += i;
    
        // // subtract all numbers in input from sum.
        // for (int num : arr)
        //     s1 -= num;
    
        // // s1, now, is the missing number
        // return s1;

        int x1 = 1;
        int x2 = arr[0];
        int N = arr.length + 1;
        for(int i = 2; i <= N; i++){
            x1 ^= i;
        }
        for(int i = 1; i < arr.length; i++){
            x2 ^= arr[i];
        }

        return x1 ^ x2;
    }
  
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 5, 2, 6, 4 };
        System.out.print("Missing number is: " + MissingNumber.findMissingNumber(arr));
    }
  }
  