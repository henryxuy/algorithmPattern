import java.util.*;

class TwoSingleNumbers {
    public static int[] findSingleNumbersXOR(int[] nums) {
        int[] resultArray = new int[2];
        int n1xn2 = 0;
        for(int num: nums){
            n1xn2 ^= num;
        }

        // get the rightmost bit that is '1', separate nums into 2 groups based on this bit
        int rightmostSetBit = 1;
        while((rightmostSetBit & n1xn2) == 0){
            rightmostSetBit = rightmostSetBit << 1;
        }
        int num1 = 0, num2 = 0;
        for(int num: nums){
            if((num & rightmostSetBit) != 0){
                // the bit is set
                num1 ^= num;
            }
            else{
                // bit is not set
                num2 ^= num;
            }
        }
        resultArray[0] = num1;
        resultArray[1] = num2;

        return resultArray;
    }


    public static int[] findSingleNumbers(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        // if not in, add. If contains, remove
        // same as XOR!
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])){
                set.remove(nums[i]);
            }
            else{
                set.add(nums[i]);
            }
        } 
        Iterator<Integer> iterator = set.iterator();
        int[] resultArray = new int[2];
        resultArray[0] = iterator.next();
        resultArray[1] = iterator.next();

        return resultArray;
    }
  
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
        int[] result = TwoSingleNumbers.findSingleNumbersXOR(arr);
        System.out.println("Single numbers are: " + result[0] + ", " + result[1]);
    
        arr = new int[] { 2, 1, 3, 2 };
        result = TwoSingleNumbers.findSingleNumbersXOR(arr);
        System.out.println("Single numbers are: " + result[0] + ", " + result[1]);
    }
  }
  
