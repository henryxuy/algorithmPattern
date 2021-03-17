import java.util.*;

public class NoRepeatSubstring {
    public static int findLength(String str){
        // map from character to previous index
        Map<Character, Integer> mapCharIndex = new HashMap<Character, Integer>();
        int start = 0, end = 0;
        int maxLen = Integer.MIN_VALUE;
        int N = str.length();

        while(end < N){
            char currentChar = str.charAt(end);
            if(!mapCharIndex.containsKey(currentChar)){
                mapCharIndex.put(currentChar, end);
            }
            else{
                int prevIndex = mapCharIndex.get(currentChar);
                mapCharIndex.put(currentChar, end);
                // this is tricky; in the current window, we will not have any 'rightChar' after its previous index
                // and if 'windowStart' is already ahead of the last index of 'rightChar', we'll keep 'windowStart'
                // (We should not go back through this)
                start = Math.max(start, prevIndex + 1);
            }

            end++;
            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }

    
}
