import java.util.*;

class MedianOfAStream {
    // use two heaps(max + min), and maintain their balance.
    // if number < top of the maxHeap, insert in the maxHeap
    // else, insert in the minHeap
    // medium is calculated from the two top elements

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());


    public void insertNum(int num) {
        int minHeapSize = minHeap.size();
        int maxHeapSize = maxHeap.size();
        int maxHeapTop;
        if(maxHeapSize == 0){
            // be sure to insert in the maxHeap if maxHeap is empty
            maxHeapTop = Integer.MAX_VALUE;
        }
        else{
            maxHeapTop = maxHeap.peek();
        }

        if(num < maxHeapTop){
            maxHeap.offer(num);
        }
        else{
            minHeap.offer(num);
        }
        // balance the heap
        if(maxHeap.size() > minHeap.size() + 1){
            // get the max from the maxHeap(which is smaller than each in the minHeap)
            // and put it into the minHeap
            int numberToMove = maxHeap.poll();
            minHeap.offer(numberToMove);
        }
        else if(minHeap.size() > maxHeap.size() + 1){
            // get the min from the minHeap(which is larger than each in the maxHeap)
            // and put it into the maxHeap
            int numberToMove = minHeap.poll();
            maxHeap.offer(numberToMove);
        }

    }

    public double findMedian() {
        if(maxHeap.size() == minHeap.size()){
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        else if(maxHeap.size() > minHeap.size()){
            return maxHeap.peek();
        }
        else{
            return minHeap.peek();
        }

    }

    public static void main(String[] args) {
        MedianOfAStream medianOfAStream = new MedianOfAStream();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
    }
}




