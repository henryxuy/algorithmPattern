import java.util.*;

public class FrequencyStack {
    // pop the most frequent number. If there is a tie, return the number which was pushed later
    int timeStamp = 0;
    HashMap<Integer, Integer> mapKeyFreq = new HashMap<>();
    PriorityQueue<Node> maxHeap = new PriorityQueue<>(new Comparator<Node>(){
        @Override
        public int compare(Node n1, Node n2){
            if(n1.freq == n2.freq){
                return Integer.compare(n2.lastTimeStamp, n1.lastTimeStamp);
            }

            return Integer.compare(n2.freq, n1.freq);
        }
    });


    private static class Node {
        int key;
        int freq = 0;
        int lastTimeStamp = -1;

        Node(int key, int freq, int timeStamp){
            this.key = key;
            this.freq = freq;
            this.lastTimeStamp = timeStamp;
        }
    }


    public void push(int num) {
        // update the freq, added time, time stamp
        if(mapKeyFreq.containsKey(num)){
            int oldFreq = mapKeyFreq.get(num);
            mapKeyFreq.put(num, oldFreq + 1);
            Node nodeNew = new Node(num, oldFreq + 1, timeStamp++);
            maxHeap.offer(nodeNew);
        }
        else{
            Node nodeNew = new Node(num, 1, timeStamp++);
            mapKeyFreq.put(num, 1);
            maxHeap.offer(nodeNew);
        }

    }
    
    public int pop() {
        Node nodeToRemove = maxHeap.poll();
        int num = nodeToRemove.key;
        if(mapKeyFreq.get(num) > 1){
            mapKeyFreq.put(num, mapKeyFreq.get(num) - 1);
        }
        else {
            mapKeyFreq.remove(num);
        }

        return num;
    }
    
    public static void main(String[] args) {
        FrequencyStack frequencyStack = new FrequencyStack();
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(3);
        frequencyStack.push(2);
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(5);
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
    }

    
}
