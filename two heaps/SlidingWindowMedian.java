import java.util.*;

class SlidingWindowMedian {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public double[] findSlidingWindowMedian(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        int N = nums.length;
        int index = 0;
        while(index < k){
            this.add(nums[index]);
            index++;
        }
        result[0] = this.medium();
        while(index < N){
            this.delete(nums[index - k]);
            this.add(nums[index]);
            result[index - k + 1] = this.medium();
            index++;
        }

        return result;
    }

    private void add(int num){
        // insert large elements in minHeap, small elements in maxHeap
        if(maxHeap.isEmpty() || num <= maxHeap.peek()){
            maxHeap.offer(num);
        }
        else{
            minHeap.offer(num);
        }
        balanceTheHeap();
    }

    private void balanceTheHeap(){
        // balance the two heaps
        // either both the heaps will have equal number of elements or max-heap will have one 
        // more element than the min-heap
        if(maxHeap.size() > minHeap.size() + 1){
            minHeap.offer(maxHeap.poll());
        }
        else if(minHeap.size() > maxHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }

    private double medium(){
        if(minHeap.size() == maxHeap.size()){
            return minHeap.peek() / 2.0 + maxHeap.peek() / 2.0;
        }
        else if(maxHeap.size() > minHeap.size()){
            // maxHeap has one more element than minHeap
            return maxHeap.peek();
        }
        else{
            return minHeap.peek();
        }
    }

    private void delete(int num){
        if(num <= maxHeap.peek()){
            maxHeap.remove(num);
        }
        else{
            minHeap.remove(num);
        }
        balanceTheHeap();
    }




    public static void main(String[] args) {
        SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
        double[] result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 2);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
        System.out.println();

        slidingWindowMedian = new SlidingWindowMedian();
        result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 3);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
    }

}