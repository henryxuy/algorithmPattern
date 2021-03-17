import java.util.*;
import java.util.*;

class TaskSchedulingOrder {
    public static List<Integer> findOrder(int tasks, int[][] prerequisites) {
        List<Integer> sortedOrder = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Queue<Integer> sourceQueue = new LinkedList<>();

        // initialize the graph
        for(int i = 0; i < tasks; i++){
            graph.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }

        // build the graph
        for(int i = 0; i < prerequisites.length; i++){
            int parent = prerequisites[i][0], child = prerequisites[i][1];
            graph.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }

        for(int i = 0; i < tasks; i++){
            if(inDegree.get(i) == 0){
                sourceQueue.add(i);
            }
        }

        // do the topological sort
        while(!sourceQueue.isEmpty()){
            int current = sourceQueue.poll();
            List<Integer> childList = graph.get(current);
            sortedOrder.add(current);
            for(Integer child: childList){
                inDegree.put(child, inDegree.get(child) - 1);
                if(inDegree.get(child) == 0){
                    sourceQueue.add(child);
                }
            }
        }
        if(sortedOrder.size() != tasks){
            return new ArrayList<>();
        }

        return sortedOrder;
    }

    public static void main(String[] args) {
        List<Integer> result = TaskSchedulingOrder.findOrder(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println(result);

        result = TaskSchedulingOrder.findOrder(3,
            new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println(result);

        result = TaskSchedulingOrder.findOrder(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
            new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println(result);
    }
}