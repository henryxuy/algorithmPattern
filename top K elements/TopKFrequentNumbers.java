import java.util.*;

class TopKFrequentNumbers {

    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k){
        // frequency map + min heap to find the k most frequent number (much better!)
        List<Integer> topNumbers = new ArrayList<>(k);
        Map<Integer, Integer> mapFreq = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> (e1.getValue() - e2.getValue()));
        for(int n: nums){
            mapFreq.put(n, mapFreq.getOrDefault(n, 0) + 1);
        }
        
        for(Map.Entry<Integer, Integer> entry: mapFreq.entrySet()){
            minHeap.offer(entry);
            // only keep top k frequent elements
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        while(!minHeap.isEmpty()){
            topNumbers.add(minHeap.poll().getKey());
        }

        return topNumbers;
    }


    public static List<Integer> findTopKFrequentNumbersTwoHashMap(int[] nums, int k) {
        List<Integer> topNumbers = new ArrayList<>(k);
        int N = nums.length;
        // 1. use two HashMaps (better for streaming data)
        HashMap<Integer, Integer> mapNumberFreq = new HashMap<>();
        TreeMap<Integer, List<Integer>> mapFreqNumber = new TreeMap<>((o1, o2) -> (o2 - o1));
        for(int i = 0; i < N; i++){
            mapNumberFreq.put(nums[i], mapNumberFreq.getOrDefault(nums[i], 0) + 1);
            int currentFreq = mapNumberFreq.get(nums[i]);
            // remove from previous entry if necessary
            if(currentFreq > 1){
                mapFreqNumber.get(currentFreq - 1).remove((Integer) nums[i]);
            }
            if(!mapFreqNumber.containsKey(currentFreq)){
                // add to current freq. entry
                mapFreqNumber.put(currentFreq, new ArrayList<>());
            }
            mapFreqNumber.get(currentFreq).add(nums[i]);
        }
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = mapFreqNumber.entrySet().iterator();
        int remainingCount = k;
        while(remainingCount > 0) {
            Map.Entry<Integer, List<Integer>> entry = iterator.next();
            List<Integer> tempList = entry.getValue();
            int countElements = tempList.size();
            for(int i = 0; i < countElements; i++){
                topNumbers.add(tempList.get(i));
                remainingCount--;
                if(remainingCount == 0){
                    break;
                }
            }
        }

        return topNumbers;
    }

    public static void main(String[] args) {
        List<Integer> result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] { 1, 3, 5, 12, 11, 12, 11 }, 2);
        System.out.println("Here are the K frequent numbers: " + result);

        result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] { 5, 12, 11, 3, 11 }, 2);
        System.out.println("Here are the K frequent numbers: " + result);
    }
}
