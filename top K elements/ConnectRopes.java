import java.util.*;

class ConnectRopes {

    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        // TODO: Write your code here
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int N = ropeLengths.length;

        for(int i = 0; i < N; i++){
            minHeap.offer(ropeLengths[i]);
        }

        // calculate the cost through the order in minHeap
        int resultSum = 0;
        int tempSum = 0;
        while(minHeap.size() > 1){
            tempSum = minHeap.poll() + minHeap.poll();
            resultSum += tempSum;
            minHeap.offer(tempSum);
        }

        return resultSum;
    }

    public static void main(String[] args) {
        int result = ConnectRopes.minimumCostToConnectRopes(new int[] { 1, 3, 11, 5 });
        System.out.println("Minimum cost to connect ropes: " + result);
        result = ConnectRopes.minimumCostToConnectRopes(new int[] { 3, 4, 5, 6 });
        System.out.println("Minimum cost to connect ropes: " + result);
        result = ConnectRopes.minimumCostToConnectRopes(new int[] { 1, 3, 11, 5, 2 });
        System.out.println("Minimum cost to connect ropes: " + result);
    }
}