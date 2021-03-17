import java.util.*;

class KLargestNumbers {

    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        // many ways to solve it: quick select, heap, median of medians, sorting-brute-force
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            maxHeap.offer(nums[i]);
        }
        for(int i = 0; i < k; i++){
            result.add(maxHeap.poll());
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = KLargestNumbers.findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);

        result = KLargestNumbers.findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);
    }
}
