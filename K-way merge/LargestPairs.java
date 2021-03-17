import java.util.*;

class LargestPairs {

    public static List<int[]> findKLargestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0] + o1[1], o2[0] + o2[1]));
        int N1 = nums1.length;
        int N2 = nums2.length;

        for(int i = 0; i < N1 && i < k; i++){
            for(int j = 0; j < N2 && j < k; j++){
                int[] numPair = new int[] {nums1[i], nums2[j]};
                if(minHeap.size() < k){
                    minHeap.offer(numPair);
                }
                else{
                    int[] pairPeek = minHeap.peek();
                    if(nums1[i] + nums2[j] > pairPeek[0] + pairPeek[1]){
                        minHeap.poll();
                        minHeap.offer(numPair);
                    }
                    else{
                        break;
                    }
                }
            }
        }

        result.addAll(minHeap);

        return result;
    }

    public static void main(String[] args) {
        int[] l1 = new int[] { 9, 8, 2 };
        int[] l2 = new int[] { 6, 3, 1 };
        List<int[]> result = LargestPairs.findKLargestPairs(l1, l2, 3);
        System.out.print("Pairs with largest sum are: ");
        for (int[] pair : result)
        System.out.print("[" + pair[0] + ", " + pair[1] + "] ");
    }
}