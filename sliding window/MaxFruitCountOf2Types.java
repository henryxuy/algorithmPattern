import java.util.*;

class MaxFruitCountOf2Types{
    public static int findLength(char[] arr){
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int start = 0, end = 0;
        int maxLen = Integer.MIN_VALUE;
        int N = arr.length;

        while(end < N){
            char currentChar = arr[end];
            map.put(currentChar, map.getOrDefault(currentChar, 0) + 1);
            end++;

            while(map.size() > 2){
                // shrink the map
                char prevChar = arr[start];
                map.put(prevChar, map.get(prevChar) - 1);
                if(map.get(prevChar) == 0){
                    map.remove(prevChar);
                }
                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }
}

