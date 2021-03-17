import java.util.*;

class TaskScheduling {
    public static boolean isSchedulingPossible(int tasks, int[][] prerequisites) {
        // prerequisites: prerequisites[idx of prerequistes][0 for parent, 1 for child]
        List<Integer> sortedOrder = new ArrayList<>();
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        Queue<Integer> sourceQueue = new LinkedList<>();

        // initialize the graph
        for(int i = 0; i < tasks; i++){
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        // build the graph
        for(int i = 0; i < prerequisites.length; i++){
            int parent = prerequisites[i][0], child = prerequisites[i][1];
            inDegree.put(child, inDegree.get(child) + 1);
            graph.get(parent).add(child);
        }

        for(int i = 0; i < tasks; i++){
            if(inDegree.get(i) == 0){
                sourceQueue.offer(i);
            }
        }

        // do the topological sort
        while(!sourceQueue.isEmpty()){
            int current = sourceQueue.poll();
            sortedOrder.add(current);
            List<Integer> childList = graph.get(current);
            for(Integer child: childList){
                inDegree.put(child, inDegree.get(child) - 1);
                if(inDegree.get(child) == 0){
                    sourceQueue.offer(child);
                }
            }
        }

        return sortedOrder.size() == tasks;
    }

    public static void main(String[] args) {

        boolean result = TaskScheduling.isSchedulingPossible(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println("Tasks execution possible: " + result);

        result = TaskScheduling.isSchedulingPossible(3,
            new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println("Tasks execution possible: " + result);

        result = TaskScheduling.isSchedulingPossible(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 },
            new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println("Tasks execution possible: " + result);
    }
}