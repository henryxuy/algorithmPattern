import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

class AlienDictionary {
    public static String findOrder(String[] words) {
        if(words == null || words.length == 0){
            return "";
        }
        // use neighbor words to determine the valid order, then use topological sort
        HashMap<Character, Integer> inDegree = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();
        String resultString = "";

        // initialize
        for(String word: words){
            for(char c: word.toCharArray()){
                inDegree.put(c, 0);
                graph.put(c, new ArrayList<>());
            }
        }

        // build the graph
        for(int i = 0; i < words.length - 1; i++){
            // word1 comes before word2, its ordering is smaller
            String word1 = words[i];
            String word2 = words[i + 1];
            // Recall the definition of ordering in dict(!)
            for(int j = 0; j < Math.min(word1.length(), word2.length()); j++){
                char parent = word1.charAt(j), child = word2.charAt(j);
                if(parent != child){
                    graph.get(parent).add(child);
                    inDegree.put(child, inDegree.get(child) + 1);
                    break;
                }
            }
        }

        // find the points with no incoming degree
        Queue<Character> sourceQueue = new LinkedList<>();
        for(Map.Entry<Character, Integer> entry: inDegree.entrySet()){
            if(entry.getValue() == 0){
                sourceQueue.offer(entry.getKey());
            }
        }

        // do the topological sort
        while(!sourceQueue.isEmpty()){
            char currentChar = sourceQueue.poll();
            List<Character> childList = graph.get(currentChar);
            resultString = resultString + String.valueOf(currentChar);
            
            for(Character child: childList){
                inDegree.put(child, inDegree.get(child) - 1);
                if(inDegree.get(child) == 0){
                    sourceQueue.offer(child);
                }
            }
        }

        if(resultString.length() != inDegree.size()){
            resultString = "";
        }

        return resultString;
    }

    public static void main(String[] args) {
        String result = AlienDictionary.findOrder(new String[] { "ba", "bc", "ac", "cab" });
        System.out.println("Character order: " + result);

        result = AlienDictionary.findOrder(new String[] { "cab", "aaa", "aab" });
        System.out.println("Character order: " + result);

        result = AlienDictionary.findOrder(new String[] { "ywx", "wz", "xww", "xz", "zyy", "zwz" });
        System.out.println("Character order: " + result);
    }
}