import java.util.*;
class LongestSubstringKDistinct {
    public static int findLength(String str, int k) {
      // TODO: Write your code here
      Map<Character, Integer> mapFreq = new HashMap<Character, Integer>();
      int N = str.length();
      int start = 0, end = 0;
      int maxLen = Integer.MIN_VALUE;

      while(end < N){
        char currentChar = str.charAt(end);
        mapFreq.put(currentChar, mapFreq.getOrDefault(currentChar, 0) + 1);
        // if(mapFreq.containsKey(currentChar)){
        //   mapFreq.put(currentChar, mapFreq.get(currentChar) + 1);
        // }
        // else{
        //   mapFreq.put(currentChar, 1);
        // }
        end++;
        while(mapFreq.size() > k){
            // shrink the map
            char prevChar = str.charAt(start);
            if(mapFreq.get(prevChar) > 1){
              mapFreq.put(prevChar, mapFreq.get(prevChar) - 1);
            }
            else{
              // the last occurrence
              mapFreq.remove(prevChar);
            }
            start++;
        }

        maxLen = Math.max(maxLen, end - start);
      }

      return maxLen;
    }
  }