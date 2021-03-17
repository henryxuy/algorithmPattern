import java.util.*;


public class TaskScheduler {

    public static int scheduleTasksAnswer(char[] tasks, int k) {
        int intervalCount = 0;
        Map<Character, Integer> taskFrequencyMap = new HashMap<>();
        for (char chr : tasks)
            taskFrequencyMap.put(chr, taskFrequencyMap.getOrDefault(chr, 0) + 1);
    
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(
            (e1, e2) -> e2.getValue() - e1.getValue());
    
        // add all tasks to the max heap
        maxHeap.addAll(taskFrequencyMap.entrySet());
    
        while (!maxHeap.isEmpty()) {
            List<Map.Entry<Character, Integer>> waitList = new ArrayList<>();
            int n = k + 1; // try to execute as many as 'k+1' tasks from the max-heap
            for (; n > 0 && !maxHeap.isEmpty(); n--) {
                intervalCount++;
                Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
                if (currentEntry.getValue() > 1) {
                    currentEntry.setValue(currentEntry.getValue() - 1);
                    waitList.add(currentEntry);
                }
            }
            maxHeap.addAll(waitList); // put all the waiting list back on the heap
            if (!maxHeap.isEmpty())
                intervalCount += n; // we'll be having 'n' idle intervals for the next iteration
        }
    
        return intervalCount;
    }


    
    public static int scheduleTasksDeprecated(char[] tasks, int k) {
        int N = tasks.length;
        HashMap<Character, Integer> freqMap = new HashMap<>();
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((o1, o2) -> (o2.getValue() - o2.getValue()));

        for(int i = 0; i < N; i++){
            char currentTask = tasks[i];
            freqMap.put(currentTask, freqMap.getOrDefault(currentTask, 0) + 1);
        }

        maxHeap.addAll(freqMap.entrySet());
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        List<Character> seqList = new LinkedList<>();
        int time = 0;

        while(!maxHeap.isEmpty() || !queue.isEmpty()){
            if(!maxHeap.isEmpty()){
                Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
                seqList.add(currentEntry.getKey());
                currentEntry.setValue(currentEntry.getValue() - 1);
                queue.offer(currentEntry);
            }
            else{
                // idle
                seqList.add('.');
                Map.Entry<Character, Integer> stuffEntry = new AbstractMap.SimpleEntry<Character, Integer>('.', 0);
                queue.offer(stuffEntry);
            }
            if(time >= k - 1 && !queue.isEmpty()){
                Map.Entry<Character, Integer> entry = queue.poll();
                if(entry.getValue() > 0){
                    maxHeap.offer(entry);
                }
            }
            time++;
        }

        for(Character ch: seqList){
            System.out.print(ch);
        }

        return seqList.size();
    }


    public static int scheduleTasks(char[] tasks, int k) {
        int N = tasks.length;
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((e1, e2) -> (e2.getValue() - e1.getValue()));
        HashMap<Character, Integer> freqMap = new HashMap<>();

        for(int i = 0; i < N; i++){
            char currentTask = tasks[i];
            freqMap.put(currentTask, freqMap.getOrDefault(currentTask, 0) + 1);
        }

        maxHeap.addAll(freqMap.entrySet());

        StringBuilder resultStr = new StringBuilder();
        while(!maxHeap.isEmpty()){
            // try to execute k + 1 tasks in batch. If cannot, have some idle times
            List<Map.Entry<Character, Integer>> waitingList = new ArrayList<>();
            int i = 0;
            for(; i < k + 1 && !maxHeap.isEmpty(); i++){
                Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
                currentEntry.setValue(currentEntry.getValue() - 1);
                resultStr.append(currentEntry.getKey());
                if(currentEntry.getValue() > 0){
                    waitingList.add(currentEntry);
                }
            }
            // first add back. if already empty, then we do not need to add additional idles
            maxHeap.addAll(waitingList);
            for(; i < k + 1 && !maxHeap.isEmpty(); i++){
                resultStr.append('.');
            }
        }
        // System.out.println(resultStr.toString());

        return resultStr.length();
    }
    

    public static void main(String[] args) {
        char[] tasks = new char[] { 'a', 'a', 'a', 'b', 'c', 'c' };
        System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 2));
    
        tasks = new char[] { 'a', 'b', 'a' };
        System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 3));
    }
}
