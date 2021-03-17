import java.util.*;

class StringAnagrams {
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        List<Integer> resultIndices = new ArrayList<Integer>();

        HashMap<Character, Integer> slidingWindow = new HashMap<>();
        HashMap<Character, Integer> patternWindow = new HashMap<>();
        int N = pattern.length();
        int stringLength = str.length();
        int matched = 0;

        // build the pattern window
        for(int i = 0; i < N; i++){
            char currentChar = pattern.charAt(i);
            patternWindow.put(currentChar, patternWindow.getOrDefault(currentChar, 0) + 1);
        }

        // sliding window to find the anagrams
        int left = 0, right = 0;
        while(right < stringLength){
            
            char currentChar = str.charAt(right);
            slidingWindow.put(currentChar, slidingWindow.getOrDefault(currentChar, 0) + 1);
            right++;

            if(slidingWindow.get(currentChar) <= patternWindow.get(currentChar)){
                matched++;
            }
            
            // shrink the window if necessary
            if(right - left > N){
                char removeChar = str.charAt(left);
                slidingWindow.put(removeChar, slidingWindow.getOrDefault(removeChar, 0) - 1);
                left++;
                if(slidingWindow.get(removeChar) + 1 <= patternWindow.get(currentChar)){
                    matched--;
                }
            }

            if(matched == N){
                resultIndices.add(left);
            }
        }
        
        return resultIndices;
    }
}

