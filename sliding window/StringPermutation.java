import java.util.*;

public class StringPermutation {
    public static boolean findPermutation(String str, String pattern) {
        // TODO: Write your code here
        int N = str.length();
        int patternSize = pattern.length();
        int start = 0, end = 0;
        int validSize = 0;                  // match if validSize == patternSize && end - start == patternSize
        Map<Character, Integer> window = new HashMap<Character, Integer>();
        Map<Character, Integer> need = new HashMap<Character, Integer>();
        // set the need map and put the corresponding chars in the window
        for(int i = 0; i < patternSize; i++){
            char c = pattern.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
            if(!window.containsKey(c)){
                // put the char we need in the window map
                window.put(c, 0);
            }
        }

        while(end < N){
            char currentChar = str.charAt(end);
            end++;
            if(window.containsKey(currentChar)){
                if(window.get(currentChar) < need.get(currentChar)){
                    validSize++;
                }
                window.put(currentChar, window.get(currentChar) + 1);
            }

            while(validSize == patternSize){
                // check whether it's exactly matched
                if(end - start == patternSize){
                    return true;
                }
                // shrink
                char prevChar = str.charAt(start);
                if(need.containsKey(prevChar)){
                    if(window.get(prevChar) <= need.get(prevChar))
                        validSize--;
                    window.put(prevChar, window.get(prevChar) - 1);
                }
                start++;
            }
        }

        return false;
    }
    
}
