import java.util.*;

class Permutations {

    public static List<List<Integer>> findPermutationsQueue(int[] nums){
        // BFS queue to find permutations (substantially k 0-1 bits)
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> levelOrderQueue = new LinkedList<>();
        List<Integer> tempResultList = new ArrayList<>();
        levelOrderQueue.offer(tempResultList);
        int N = nums.length;
        int currentAddIndex = 0;
        while(currentAddIndex < N){
            int numberToAdd = nums[currentAddIndex];
            int levelSize = levelOrderQueue.size();
            for(int i = 0; i < levelSize; i++){
                List<Integer> tempList = levelOrderQueue.poll();
                List<Integer> tempListNew = new ArrayList<>(tempList);
                tempListNew.add(numberToAdd);

                levelOrderQueue.offer(tempList);
                levelOrderQueue.offer(tempListNew);
            }
            currentAddIndex++;
        }

        while(!levelOrderQueue.isEmpty()){
            result.add(levelOrderQueue.poll());
        }

        return result;
    }


    public static List<List<Integer>> findPermutationsLoop(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.offer(new ArrayList<>());

        for(int currentNumber: nums){
            // take all existing permutations and add the current number to create new permutations
            int n = permutations.size();
            for(int i = 0; i < n; i++){
                List<Integer> tempPermutation = permutations.poll();
                
                for(int j = 0; j <= tempPermutation.size(); j++){
                    List<Integer> newPermutation = new ArrayList<>(tempPermutation);
                    newPermutation.add(j, currentNumber);
                    if(newPermutation.size() == nums.length){
                        result.add(newPermutation);
                    }
                    else{
                        permutations.offer(newPermutation);
                    }
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> choiceList = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int N = nums.length;
        for(Integer numInteger: nums){
            choiceList.add(numInteger);
        }

        backtrackHelper(choiceList, path, result, N);

        return result;
    }

    private static void backtrackHelper(List<Integer> choiceList, List<Integer> path, List<List<Integer>> resultList, int N){
        // exit condition
        if(choiceList.isEmpty()){
            resultList.add(new ArrayList<>(path));
            return;
        }
        // backtrack = path + choice list + exit condition + loop-recursion-withdrawl
        int numberChoices = choiceList.size();
        List<Integer> tempChoiceList = new ArrayList<>(choiceList);
        for(int i = 0; i < numberChoices; i++){
            int numberToAdd = choiceList.get(i);
            tempChoiceList.remove((Integer)numberToAdd);
            path.add(numberToAdd);

            backtrackHelper(tempChoiceList, path, resultList, N);

            tempChoiceList.add(numberToAdd);
            path.remove(path.size() - 1);
        }
    }

    private static void generatePermutationsRecursive(int[] nums, int index, List<Integer> currentPermutation, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(currentPermutation);
        } 
        else{
            // create a new permutation by adding the current number at every position
            for (int i = 0; i <= currentPermutation.size(); i++) {
                List<Integer> newPermutation = new ArrayList<Integer>(currentPermutation);
                newPermutation.add(i, nums[index]);
                generatePermutationsRecursive(nums, index + 1, newPermutation, result);
            }
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> result = Permutations.findPermutationsQueue(new int[] { 1, 3, 5 });
        System.out.print("Here are all the permutations: " + result);
    }
}
