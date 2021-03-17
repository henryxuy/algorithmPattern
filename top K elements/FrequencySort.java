import java.util.*;

class FrequencySort {

    public static String sortCharacterByFrequency(String str) {
        // sort a string based on the decreasing frequency of its characters
        HashMap<Character, Integer> mapCharFreq = new HashMap<>();
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((o1, o2) -> (o2.getValue() - o1.getValue()));
        String resultString = "";
        int N = str.length();
        for(int i = 0; i < N; i++){
            char currentChar = str.charAt(i);
            mapCharFreq.put(currentChar, mapCharFreq.getOrDefault(currentChar, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry: mapCharFreq.entrySet()){
            maxHeap.offer(entry);
        }

        while(!maxHeap.isEmpty()){
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            String addedString = String.valueOf(entry.getKey()).repeat(entry.getValue());
            resultString = resultString + addedString;
        }

        return resultString;
    }

    public static void main(String[] args) {
        String result = FrequencySort.sortCharacterByFrequency("Programming");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);

        result = FrequencySort.sortCharacterByFrequency("abcbab");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);
    }
}