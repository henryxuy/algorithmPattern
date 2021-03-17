import java.util.*;

public class WordConcatenation {
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        // Note: all words have the same length
        // save the frequency of each word in a HashMap
        // go through the str to match the window with wordMap

        List<Integer> resultIndices = new ArrayList<Integer>();
        HashMap<String, Integer> wordMap = new HashMap<>();
        int numberOfWords = words.length;
        int wordLength = words[0].length();
        int stringLength = str.length();

        for(int i = 0; i < numberOfWords; i++){
            String currentWord = words[i];
            wordMap.put(currentWord, wordMap.getOrDefault(currentWord, 0) + 1);
        }

        // go through the str using sliding window
        for(int start = 0; start < stringLength - numberOfWords * wordLength + 1; start++){
            // see whether [start, start + numberOfWords * wordLength] matches the wordMap
            HashMap<String, Integer> windowMap = new HashMap<>();
            int matchedCount = 0;
            for(int wordCount = 0; wordCount < numberOfWords; wordCount++){
                String currentWord = str.substring(start + wordCount*wordLength, start + (wordCount + 1)*wordLength);
                windowMap.put(currentWord, windowMap.getOrDefault(currentWord, 0) + 1);
                if(!wordMap.containsKey(currentWord)){
                    // different words are not allowed here
                    break;
                }
                else if(windowMap.get(currentWord) <= wordMap.getOrDefault(currentWord, 0)){
                    matchedCount++;
                }
            }

            if(matchedCount == numberOfWords){
                resultIndices.add(start);
            }
        }


        return resultIndices;
    }


}
