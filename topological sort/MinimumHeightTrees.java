import java.util.*;

class MinimumHeightTrees {
    public static List<Integer> findTrees(int nodes, int[][] edges) {
        // find the root to make the height of tree minimum
        // keep removing leaf nodes (whose degree = 1), and the final remaining 1/2 nodes would be our result
        List<Integer> minHeightTrees = new ArrayList<>();
        if(nodes <= 0){
            return minHeightTrees;
        }
        if(nodes == 1){
            minHeightTrees.add(0);
            return minHeightTrees;
        }

        // initialize the graph
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        for(int i = 0; i < nodes; i++){
            graph.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }

        // build the graph
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            graph.get(from).add(to);
            graph.get(to).add(from);
            inDegree.put(from, inDegree.get(from) + 1);
            inDegree.put(to, inDegree.get(to) + 1);
        }

        // find all leaf nodes (nodes with degree = 1)
        Queue<Integer> leafQueue = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()){
            if(entry.getValue() == 1){
                leafQueue.offer(entry.getKey());
            }
        }
        // remove nodes through loop
        int totalNodes = nodes;
        while(totalNodes > 2){

            // remove all leaves
            int leafSize = leafQueue.size();
            totalNodes -= leafSize;
            for(int i = 0; i < leafSize; i++){
                int nodeToRemove = leafQueue.poll();
                // update the corresponding DSs
                List<Integer> neighborList = graph.get(nodeToRemove);
                for(Integer neighborVertex: neighborList){
                    inDegree.put(neighborVertex, inDegree.get(neighborVertex) - 1);
                    if(inDegree.get(neighborVertex) == 1){
                        leafQueue.offer(neighborVertex);
                    }
                }
            }
        }
        minHeightTrees.addAll(leafQueue);

        return minHeightTrees;
    }

    public static void main(String[] args) {
        List<Integer> result = MinimumHeightTrees.findTrees(5,
            new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 2, 4 } });
        System.out.println("Roots of MHTs: " + result);

        result = MinimumHeightTrees.findTrees(4,
            new int[][] { new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 2, 3 } });
        System.out.println("Roots of MHTs: " + result);

        result = MinimumHeightTrees.findTrees(4,
            new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 } });
        System.out.println("Roots of MHTs: " + result);
    }
}