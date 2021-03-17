import java.util.*;

public class RearrangeString {

    private static class CharWithFreq{
        char ch;
        int freq;

        CharWithFreq(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }

    }

    public static String rearrangeString(String str) {
        int N = str.length();
        HashMap<Character, Integer> freqMap = new HashMap<>();
        PriorityQueue<CharWithFreq> maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.freq, o1.freq));

        for(int i = 0; i < N; i++){
            char currentChar = str.charAt(i);
            freqMap.put(currentChar, freqMap.getOrDefault(currentChar, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry: freqMap.entrySet()){
            CharWithFreq currentCharInstance = new CharWithFreq(entry.getKey(), entry.getValue());
            maxHeap.offer(currentCharInstance);
        }
        // construct the string
        StringBuilder resultStr = new StringBuilder();
        CharWithFreq previousCharInstance = null;
        while(!maxHeap.isEmpty()){
            // add the char in intersected order. poll() and reserve it to avoid adding it twice continuously
            CharWithFreq currentCharInstance = maxHeap.poll();
            resultStr.append(currentCharInstance.ch);
            currentCharInstance.freq--;

            if(previousCharInstance != null){
                maxHeap.offer(previousCharInstance);
            }

            previousCharInstance = currentCharInstance.freq == 0 ? null : currentCharInstance;

        }

        return resultStr.length() == str.length() ? resultStr.toString() : "";
    }


    public static String rearrangeStringAnswer(String str) {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
    
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(
                (e1, e2) -> e2.getValue() - e1.getValue());
    
        // add all characters to the max heap
        maxHeap.addAll(charFrequencyMap.entrySet());
    
        Map.Entry<Character, Integer> previousEntry = null;
        StringBuilder resultString = new StringBuilder(str.length());
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            // add the previous entry back in the heap if its frequency is greater than zero
            if (previousEntry != null && previousEntry.getValue() > 0)
                maxHeap.offer(previousEntry);
            // append the current character to the result string and decrement its count
            resultString.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue() - 1);
            previousEntry = currentEntry;
        }
        // if we were successful in appending all the characters to the result string, return it
        return resultString.length() == str.length() ? resultString.toString() : "";
    }


    public static void main(String[] args) {
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aappp"));
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("Programming"));
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aapa"));
    }
    
}
