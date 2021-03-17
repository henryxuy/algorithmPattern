import java.util.*;


public class SumOfElements {
    public static int findSumOfElements(int[] nums, int k1, int k2) {
        int N = nums.length;
        // PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // reverse of the natural order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < N; i++){
            if(maxHeap.size() < k2){
                maxHeap.offer(nums[i]);
            }
            else{
                // size == k2
                // track the k2-smallest numbers in the maxHeap
                if(nums[i] < maxHeap.peek()){
                    maxHeap.poll();
                    maxHeap.offer(nums[i]);
                }
            }
        }
        // take sum of k1 to k2 smallest numbers
        // just take the sum of the largest k2 - k1 - 1 numbers in the maxHeap (excluding the largest one)
        int sum = 0;
        maxHeap.poll();
        for(int i = 0; i < k2 - k1 - 1; i++){
            sum += maxHeap.poll();
        }

        return sum;
    }
    
      public static void main(String[] args) {
        int result = SumOfElements.findSumOfElements(new int[] { 1, 3, 12, 5, 15, 11 }, 3, 6);
        System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
    
        result = SumOfElements.findSumOfElements(new int[] { 3, 5, 8, 7 }, 1, 4);
        System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
    }
    
}
