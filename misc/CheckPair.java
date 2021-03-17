import java.util.HashMap;

import java.util.*;

//Hash Set  =>  HashSet<Integer> hSet = new HashSet<>();
//HashMap   =>  HashMap<Integer,String> hMap = new HashMap<>();  
//HashTable =>  Hashtable<Integer,String> hTable = new Hashtable<>();  
//Hash Set Functions => {add(), remove(), contains()}
//Hash Map and Table Functions => {put(key,value), get(key), remove(key), containsKey(key), containsValue(value)}
class CheckPair {

    public static String findPair(int[] arr) {
        // find two pairs or 4 elements (a,b,c,d) such that a + b = c + d
        String result = "";
        // similar to 2sum (map target to prev. index), here we map sum to index pair
        Map<Integer, int[]> mapSumPair = new HashMap<>();
        int N = arr.length;
        // double loop: for i in range(N), for j in range(M)
        // if symmetric: for i in range(0, N), for j in range(i+1, N)
        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                // symmetric, so j starts from i + 1
                int sum = arr[i] + arr[j];
                if(!mapSumPair.containsKey(sum)){
                    mapSumPair.put(sum, new int[]{arr[i], arr[j]});
                }
                else{
                    // find the result
                    int[] previousPair = mapSumPair.get(sum);
                    result += "{" + previousPair[0] + "," + previousPair[1] + "}{" + arr[i] + "," + arr[j] + "}";
                    return result;
                }
            }
        }

        // not found   
        return result; 
    }
  }