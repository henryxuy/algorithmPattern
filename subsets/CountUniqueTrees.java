import java.util.*;

public class CountUniqueTrees {
    // count the number of valid BSTs.
    // can use memorization to optimize
    private static HashMap<Integer, Integer> countMap = new HashMap<>();

    public int countTrees(int n) {

        return countTreesDiff(n);
    }

    private int countTreesDiff(int diff){
        if(diff <= 1){
            return 1;
        }

        if(countMap.containsKey(diff)){
            return countMap.get(diff);
        }

        int countSum = 0;
        for(int i = 1; i <= diff; i++){
            int leftCount = countTreesDiff(i - 1);
            int rightCount = countTreesDiff(diff - i);

            countSum += (leftCount * rightCount);
        }
        countMap.putIfAbsent(diff, countSum);

        return countSum;
    }   

      
    public static void main(String[] args) {
        CountUniqueTrees ct = new CountUniqueTrees();
        int count = ct.countTrees(5);
        System.out.print("Total trees: " + count);
    }
    
}
