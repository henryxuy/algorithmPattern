import java.util.*;

import java.util.*;

class TopologicalSort {
  public static List<Integer> sort(int vertices, int[][] edges){
    List<Integer> sortedOrder = new ArrayList<>();
    if(vertices <= 0){
        return sortedOrder;
    }
    // a. initialize the graph into neighborList representation and count the in-degree
    HashMap<Integer, Integer> inDegree = new HashMap<>();
    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    for(int i = 0; i < vertices; i++){
        inDegree.put(i, 0);
        graph.put(i, new ArrayList<Integer>());
    }

    // b. build the graph
    for(int i = 0; i < edges.length; i++){
        // note: edges[number_of_edge][0 or 1], 0: parent, 1: child
        int parent = edges[i][0], child = edges[i][1];
        inDegree.put(child, inDegree.get(child) + 1);
        graph.get(parent).add(child);
    }

    // c. find all sources (with 0 in-degree)
    Queue<Integer> sourceQueue = new LinkedList<>();
    for(int i = 0; i < vertices; i++){
        if(inDegree.get(i) == 0){
            sourceQueue.offer(i);
        }
    }

    // d. sort. For each source, do the following:
    // add it to the sorted list, get all its children
    // decrement the in=degree of each child by 1
    // if a child's in-degree becomes '0', add it into the queue
    // repeat these steps until queue is empty
    while(!sourceQueue.isEmpty()){
        int currentSource = sourceQueue.poll();
        sortedOrder.add(currentSource);
        List<Integer> neighborList = graph.get(currentSource);
        for(Integer childNode: neighborList){
            inDegree.put(childNode, inDegree.get(childNode) - 1);
            if(inDegree.get(childNode) == 0){
                sourceQueue.offer(childNode);
            }
        }
    }

    if(sortedOrder.size() != vertices){
        // not DAG, has a cycle, topological sort is not possible
        return new ArrayList<>();
    }

    return sortedOrder;
}


  public static void main(String[] args) {
    List<Integer> result = TopologicalSort.sort(4,
        new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
    System.out.println(result);

    result = TopologicalSort.sort(5, new int[][] { new int[] { 4, 2 }, new int[] { 4, 3 }, new int[] { 2, 0 },
        new int[] { 2, 1 }, new int[] { 3, 1 } });
    System.out.println(result);

    result = TopologicalSort.sort(7, new int[][] { new int[] { 6, 4 }, new int[] { 6, 2 }, new int[] { 5, 3 },
        new int[] { 5, 4 }, new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 } });
    System.out.println(result);
  }
}