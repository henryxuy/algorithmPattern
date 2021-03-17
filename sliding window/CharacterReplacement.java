import java.util.*;

public class CharacterReplacement {
    public static int findLength(String str, int k) {
        int windowStart = 0, maxLength = 0, maxRepeatLetterCount = 0;
        Map<Character, Integer> letterFrequencyMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
          char rightChar = str.charAt(windowEnd);
          letterFrequencyMap.put(rightChar, letterFrequencyMap.getOrDefault(rightChar, 0) + 1);
          maxRepeatLetterCount = Math.max(maxRepeatLetterCount, letterFrequencyMap.get(rightChar));
    
          // current window size is from windowStart to windowEnd, overall we have a letter which is
          // repeating 'maxRepeatLetterCount' times, this means we can have a window which has one letter 
          // repeating 'maxRepeatLetterCount' times and the remaining letters we should replace.
          // if the remaining letters are more than 'k', it is the time to shrink the window as we
          // are not allowed to replace more than 'k' letters
          while (windowEnd - windowStart + 1 - maxRepeatLetterCount > k) {     // while or if are both ok
            char leftChar = str.charAt(windowStart);
            letterFrequencyMap.put(leftChar, letterFrequencyMap.get(leftChar) - 1);
            windowStart++;
          }
          // Note that the sliding window never shrinks, so we can keep a global count
          // if the count decreases, we can never get better result.
    
          maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
    
        return maxLength;
    }


    // private static int calculateRemainingOccurrence(Map<Character, Integer> mapFreq, char dominatingChar){
    //     // calcaute the # of non-dominating chars
    //     int occurrence = 0;
    //     for(Map.Entry<Character, Integer> entry: mapFreq.entrySet()){
    //         char currentChar = entry.getKey();
    //         int occurCurrent = entry.getValue();
    //         if(currentChar != dominatingChar){
    //             occurrence += occurCurrent;
    //         }
    //     }

    //     return occurrence;
    // }


    
}
