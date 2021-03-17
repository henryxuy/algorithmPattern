import java.util.HashSet;

class SingleNumber {
    public static int findSingleNumber(int[] arr) {
        int result = arr[0];
        for(int i = 1; i < arr.length; i++){
            result ^= arr[i];
        }

        return result;
    }

    public static int findSingleNumberSet(int[] arr){
        HashSet<Integer> set = new HashSet<>();
        // if not in, add. If contains, remove
        // same as XOR!
        for(int i = 0; i < arr.length; i++){
            if(set.contains(arr[i])){
                set.remove(arr[i]);
            }
            else{
                set.add(arr[i]);
            }
        } 

        return set.iterator().next();
    }
  
    public static void main( String args[] ) {
         System.out.println(findSingleNumberSet(new int[]{1, 4, 2, 1, 3, 2, 3}));
    }
  }