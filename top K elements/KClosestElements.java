import java.util.*;

class Entry {
    int key;
    int value;

    public Entry(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class KClosestElements {

    public static List<Integer> findClosestElements(int[] arr, int K, Integer X) {
        // given a sorted array, find k closest number to x in the array
        // first use binary search to find 'Y' nearest to 'X'
        // take PQ in both directions, and use priority queue to store their distance to X
        List<Integer> result = new ArrayList<>();
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while(left <= right){
            // use left to store the searched result
            mid = left + (right - left) / 2;
            if(arr[mid] == X){
                left = mid;
                break;
            }
            else if(arr[mid] < X){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        // out of loop: left = right + 1
        if(left > 0){
            left--;
        }
        // expand k at both sides
        int low = left - K;
        int high = left + K;
        low = Math.max(low, 0);
        high = Math.min(high, arr.length - 1);

        // keep the k nearest elements in the maxHeap
        PriorityQueue<Entry> maxHeap = new PriorityQueue<>((e1, e2) -> (e2.value - e1.value));
        for(int i = low; i <= high; i++){
            Entry tempEntry = new Entry(arr[i], Math.abs(arr[i] - X));
            if(maxHeap.size() < K){
                maxHeap.offer(tempEntry);
            }
            else{
                // only add smaller elements
                if(tempEntry.value < maxHeap.peek().value){
                    maxHeap.poll();
                    maxHeap.offer(tempEntry);
                }
            }
        }

        for(int i = 0; i < K; i++){
            result.add(maxHeap.poll().key);
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = KClosestElements.findClosestElements(new int[] { 5, 6, 7, 8, 9 }, 3, 7);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = KClosestElements.findClosestElements(new int[] { 2, 4, 5, 6, 9 }, 3, 6);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = KClosestElements.findClosestElements(new int[] { 2, 4, 5, 6, 9 }, 3, 10);
        System.out.println("'K' closest numbers to 'X' are: " + result);
    }
}
