import java.util.*;

class GenerateParentheses {
    // public static List<String> generateValidParentheses(int num) {
    //     List<String> result = new ArrayList<String>();
    //     Stack<Character> pathStack = new Stack<>();

    //     backtrackHelper(num, num, pathStack, result, 0);

    //     return result;
    // }

    // private static void backtrackHelper(int left, int right, Stack<Character> pathStack, List<String> resultList, int index){
    //     if(left > right){
    //         // if available # of left > # of right, then current # of left is < # of right
    //         // which is not valid.
    //         return;
    //     }

    //     if(left < 0 || right < 0){
    //         return;
    //     }

    //     if(left == 0 && right == 0){
    //         String resultString = String.valueOf(pathStack.toArray());
    //         resultList.add(resultString);
    //     }

    //     // backtrack for '(' and ')'
    //     pathStack.push('(');
    //     backtrackHelper(left - 1, right, pathStack, resultList, index + 1);
    //     pathStack.pop();

    //     pathStack.push(')');
    //     backtrackHelper(left, right - 1, pathStack, resultList, index + 1);
    //     pathStack.pop();
    // }

    public static List<String> generateValidParentheses(int num) {
        List<String> result = new ArrayList<String>();
        char[] charArray = new char[2 * num];

        backtrackHelper(num, num, charArray, result, 0);

        return result;
    }

    private static void backtrackHelper(int left, int right, char[] charArray, List<String> resultList, int index){
        if(left > right){
            // if available # of left > # of right, then current # of left is < # of right
            // which is not valid.
            return;
        }

        if(left < 0 || right < 0){
            return;
        }

        if(left == 0 && right == 0){
            String resultString = String.valueOf(charArray);
            resultList.add(resultString);
            return;
        }

        // backtrack for '(' and ')'
        charArray[index] = '(';
        backtrackHelper(left - 1, right, charArray, resultList, index + 1);
        // charArray[index] = '.';

        charArray[index] = ')';
        backtrackHelper(left, right - 1, charArray, resultList, index + 1);
        // charArray[index] = '.';
    }



    public static void main(String[] args) {
        List<String> result = GenerateParentheses.generateValidParentheses(2);
        System.out.println("All combinations of balanced parentheses are: " + result);

        result = GenerateParentheses.generateValidParentheses(3);
        System.out.println("All combinations of balanced parentheses are: " + result);
    }
}
