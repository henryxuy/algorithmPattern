import java.util.*;

class Node{
    int listIndex;
    int value;
    int index;

    Node(int listIndex, int index, int value){
        this.listIndex = listIndex;
        this.index = index;
        this.value = value;
    }
}

class KthSmallestInMSortedArrays {
    

    public static int findKthSmallest(List<Integer[]> lists, int k) {
        // given M sorted arrays (ascending), find the kth smallest number among all the arrays
        // need to track which array does the number come from.
        PriorityQueue<Node> minHeap = new PriorityQueue<>((o1, o2) -> (o1.value - o2.value));
        int numberLists = lists.size();
        
        // int heapSize = Math.max(numberLists, k);
        for(int i = 0; i < numberLists; i++){
            Node tempHeadNode = new Node(i, 0, lists.get(i)[0]);
            minHeap.offer(tempHeadNode);
        }
        int result = 0;
        for(int i = 0; i < k; i++){
            Node current = minHeap.poll();
            result = current.value;
            current.index++;
            if(current.index < lists.get(current.listIndex).length){
                current.value = lists.get(current.listIndex)[current.index];
                minHeap.offer(current);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 2, 6, 8 };
        Integer[] l2 = new Integer[] { 3, 6, 7 };
        Integer[] l3 = new Integer[] { 1, 3, 4 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int result = KthSmallestInMSortedArrays.findKthSmallest(lists, 5);
        System.out.print("Kth smallest number is: " + result);
    }
}