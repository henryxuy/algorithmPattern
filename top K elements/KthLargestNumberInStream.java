import java.util.*;

class KthLargestNumberInStream {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int k;
  
    public KthLargestNumberInStream(int[] nums, int k) {
        this.k = k;
        int N = nums.length;
        for(int i = 0; i < k; i++){
            minHeap.offer(nums[i]);
        }
        for(int i = k; i < N; i++){
            if(nums[i] > minHeap.peek()){
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
    }

    public int add(int num) {
        if(num > minHeap.peek()){
            minHeap.poll();
            minHeap.offer(num);
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] input = new int[] { 3, 1, 5, 12, 2, 11 };
        KthLargestNumberInStream kthLargestNumber = new KthLargestNumberInStream(input, 4);
        System.out.println("4th largest number is: " + kthLargestNumber.add(6));
        System.out.println("4th largest number is: " + kthLargestNumber.add(13));
        System.out.println("4th largest number is: " + kthLargestNumber.add(4));
    }
}
