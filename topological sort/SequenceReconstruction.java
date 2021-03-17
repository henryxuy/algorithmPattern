import java.util.*;

class SequenceReconstruction {
    public static boolean canConstruct(int[] originalSeq, int[][] sequences) {
        if (originalSeq.length <= 0)
            return false;
        // Given a sequence, write a method to find if originalSeq  
        // can be reconstructed from the array of sequences

        // i.e. judge whether originalSeq is a valid topological sort of sequences
        int N = originalSeq.length;
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();

        // initialize the graph
        for (int[] seq : sequences) {
            for (int i = 0; i < seq.length; i++) {
              inDegree.putIfAbsent(seq[i], 0);
              graph.putIfAbsent(seq[i], new ArrayList<Integer>());
            }
        }
        // build the graph
        for(int i = 0; i < sequences.length; i++){
            int[] tempSequenceArray = sequences[i];
            for(int j = 0; j < tempSequenceArray.length - 1; j++){
                int vertexFrom = sequences[i][j];
                int vertexTo = sequences[i][j + 1];
                graph.get(vertexFrom).add(vertexTo);
                inDegree.put(vertexTo, inDegree.get(vertexTo) + 1);
            }
        }
        // build the source queue
        Queue<Integer> sourceQueue = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()){
            if(entry.getValue() == 0){
                sourceQueue.offer(entry.getKey());
            }
        }

        // check whether the given sequence is a valid topological sort
        for(int i = 0; i < N; i++){
            int currentVertex = originalSeq[i];
            // check whether it's valid. If valid, go through it and update other data structures
            if(sourceQueue.size() != 1 || currentVertex != sourceQueue.peek()){
                // uniquely construct
                return false;
            }
            sourceQueue.remove((Integer) currentVertex);
            List<Integer> neighborList = graph.get(currentVertex);
            for(Integer neighborVertex: neighborList){
                inDegree.put(neighborVertex, inDegree.get(neighborVertex) - 1);
                if(inDegree.get(neighborVertex) == 0){
                    sourceQueue.offer(neighborVertex);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        boolean result = SequenceReconstruction.canConstruct(new int[] { 1, 2, 3, 4 },
            new int[][] { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 3, 4 } });
        System.out.println("Can we uniquely construct the sequence: " + result);

        result = SequenceReconstruction.canConstruct(new int[] { 1, 2, 3, 4 },
            new int[][] { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 2, 4 } });
        System.out.println("Can we uniquely construct the sequence: " + result);

        result = SequenceReconstruction.canConstruct(new int[] { 3, 1, 4, 2, 5 },
            new int[][] { new int[] { 3, 1, 5 }, new int[] { 1, 4, 2, 5 } });
        System.out.println("Can we uniquely construct the sequence: " + result);
    }
}