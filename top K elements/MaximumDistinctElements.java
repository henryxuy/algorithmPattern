import java.util.*;

class MaximumDistinctElements {

    public static int findMaximumDistinctElements(int[] nums, int k) {
        // remove 'K' numbers from the array such that we are left with maximum
        // "distinct" numbers. Use a minHeap
        // We count the freq., and remove numbers with the lowest freqencies >= 2 to make them distinct
        HashMap<Integer, Integer> mapFreq = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> (e1.getValue() - e2.getValue()));
        int N = nums.length;
        int distinctCount = 0;
        for(int i = 0; i < N; i++){
            mapFreq.put(nums[i], mapFreq.getOrDefault(nums[i], 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry: mapFreq.entrySet()){
            if(entry.getValue() == 1){
                distinctCount++;
            }
            else{
                minHeap.offer(entry);
            }
        }

        // following a greedy approach, try removing the least frequent numbers first from the min-heap
        int remainingToDelete = k;
        while(remainingToDelete > 0 && !minHeap.isEmpty()){
            Map.Entry<Integer, Integer> tempEntry = minHeap.poll();
            remainingToDelete -= tempEntry.getValue() - 1;
            if(remainingToDelete >= 0){
                distinctCount++;
            }
        }
        if(remainingToDelete > 0){
            // we have to remove some distinct numbers
            distinctCount -= remainingToDelete;
        }

        return Math.max(0, distinctCount);
    }

    public static void main(String[] args) {
        int result = MaximumDistinctElements.findMaximumDistinctElements(new int[] { 7, 3, 5, 8, 5, 3, 3 }, 2);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);

        result = MaximumDistinctElements.findMaximumDistinctElements(new int[] { 3, 5, 12, 11, 12 }, 3);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);

        result = MaximumDistinctElements.findMaximumDistinctElements(new int[] { 1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5 }, 2);
        System.out.println("Maximum distinct numbers after removing K numbers: " + result);
    }
}