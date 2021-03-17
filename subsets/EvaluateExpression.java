import java.util.*;

public class EvaluateExpression {
    // use memorization
    private static HashMap<String, List<Integer>> subResultMap = new HashMap<>();

    public static List<Integer> diffWaysToEvaluateExpression(String input) {
        // memo
        if(subResultMap.containsKey(input)){
            return subResultMap.get(input);
        }

        // cut the expression by half when we meet the operator
        // calculate the result recursively
        List<Integer> result = new ArrayList<>();

        // base case: only one number
        if(!input.contains("+") && !input.contains("-") && !input.contains("*")){
            result.add(Integer.parseInt(input));
        }
        else{
            // go through the string char by char
            // break the string into halves when we meet an operator
            for(int i = 0; i < input.length(); i++){
                char currentChar = input.charAt(i);
                if(!Character.isDigit(currentChar)){
                    // break the string into two halves and calculate them recursively
                    List<Integer> leftResult = diffWaysToEvaluateExpression(input.substring(0, i));
                    List<Integer> rightResult = diffWaysToEvaluateExpression(input.substring(i + 1));
                    // combine the calculated result (!!!)
                    for(int part1: leftResult){
                        for(int part2: rightResult){
                            if(currentChar == '+'){
                                result.add(part1 + part2);
                            }
                            else if(currentChar == '-'){
                                result.add(part1 - part2);
                            }
                            else if(currentChar == '*'){
                                result.add(part1 * part2);
                            }
                        }
                    }
                }
            }
        }
        subResultMap.put(input, result);

        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = EvaluateExpression.diffWaysToEvaluateExpression("1+2*3");
        System.out.println("Expression evaluations: " + result);
    
        result = EvaluateExpression.diffWaysToEvaluateExpression("2*3-4-5");
        System.out.println("Expression evaluations: " + result);
    }
    
}
