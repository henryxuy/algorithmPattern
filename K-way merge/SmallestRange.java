import java.util.*;

class RangeNode{
    int listIndex;
    int index;
    int value;

    RangeNode(int listIndex, int index, int value){
        this.listIndex = listIndex;
        this.index = index;
        this.value = value;
    }
}


class SmallestRange {

    public static int[] findSmallestRange(List<Integer[]> lists) {
        // given 'M' sorted arrays, find the smallest range that includes at least one number
        // from each of the 'M' lists

        int minRange = Integer.MAX_VALUE;
        int currentMaxNumber = 0;
        PriorityQueue<RangeNode> minHeap = new PriorityQueue<>((o1, o2) -> (o1.value - o2.value));
        int numberList = lists.size();
        int[] result = new int[2];
        Arrays.fill(result, -1);

        for(int i = 0; i < numberList; i++){
            if(lists.get(i) != null){
                RangeNode tempHeadNode = new RangeNode(i, 0, lists.get(i)[0]);
                currentMaxNumber = Math.max(currentMaxNumber, lists.get(i)[0]);
                minHeap.offer(tempHeadNode);
            }
        }

        while(minHeap.size() == lists.size()){
            // poll the smallest node, update the range and currentMax (+ result), and insert the next node
            RangeNode current = minHeap.poll();
            int currentRange = currentMaxNumber - current.value;
            if(currentRange < minRange){
                minRange = currentRange;
                result[0] = current.value;
                result[1] = currentMaxNumber;
            }
            current.index++;
            if(current.index < lists.get(current.listIndex).length){
                current.value = lists.get(current.listIndex)[current.index];
                minHeap.offer(current);
                currentMaxNumber = Math.max(currentMaxNumber, current.value);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 1, 5, 8 };
        Integer[] l2 = new Integer[] { 4, 12 };
        Integer[] l3 = new Integer[] { 7, 8, 10 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int[] result = SmallestRange.findSmallestRange(lists);
        System.out.print("Smallest range is: [" + result[0] + ", " + result[1] + "]");
    }
}
