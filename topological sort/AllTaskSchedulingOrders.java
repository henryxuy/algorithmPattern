import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.*;

class AllTaskSchedulingOrders {
    public static void printOrders(int tasks, int[][] prerequisites) {
        List<Integer> sortedOrder = new ArrayList<>();
        if(tasks <= 0){
            return;
        }
        // initialize the graph
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        for(int i = 0; i < tasks; i++){
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        // build the graph
        for(int i = 0; i < prerequisites.length; i++){
            int parent = prerequisites[i][0], child = prerequisites[i][1];
            graph.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }

        // find all sources
        LinkedList<Integer> sourceQueue = new LinkedList<>();
        for(int i = 0; i < tasks; i++){
            if(inDegree.get(i) == 0){
                sourceQueue.offer(i);
            }
        }

        topologicalSortHelper(graph, inDegree, sourceQueue, sortedOrder);
    }


    // use backtracking to search all topological sorts
    // at any stage, if we have more than one source available, we can choose any source
    // use backtracking at that time
    private static void topologicalSortHelper(HashMap<Integer, List<Integer>> graph, HashMap<Integer, Integer> inDegree,
    Queue<Integer> sourceQueue, List<Integer> sortedOrder){
        // backtracking = exit condition + choice list (what we loop on) + loop-recursion + do and withdraw
        // do backtrack on elements in sourceQueue
        if(sourceQueue.isEmpty()){
            if(sortedOrder.size() == graph.size()){
                System.out.println(sortedOrder);
            }
            return;
        }
        for(Integer source: sourceQueue){
            sortedOrder.add(source);
            Queue<Integer> sourceNextCall = cloneQueue(sourceQueue);
            sourceNextCall.remove(source);
            // update the indegree
            List<Integer> childList = graph.get(source);
            for(Integer child: childList){
                inDegree.put(child, inDegree.get(child) - 1);
                if(inDegree.get(child) == 0){
                    sourceNextCall.offer(child);
                }
            }
            // recursion
            topologicalSortHelper(graph, inDegree, sourceNextCall, sortedOrder);
            // since we operate on sourceNextCall, no need to withdraw on the original queue
            // withdraw operations on sortedOrder and inDegree
            sortedOrder.remove(source);
            for(Integer child: childList){
                inDegree.put(child, inDegree.get(child) + 1);
            }
        }
    }

                                        
    private static Queue<Integer> cloneQueue(Queue<Integer> originalQueue){
        Queue<Integer> clonedQueue = new LinkedList<>();
        for(Integer element: originalQueue){
            clonedQueue.offer(element);
        }

        return clonedQueue;
    }



    


    public static void main(String[] args) {
        AllTaskSchedulingOrders.printOrders(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println();

        AllTaskSchedulingOrders.printOrders(4,
            new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println();

        AllTaskSchedulingOrders.printOrders(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
            new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println();
    }
}