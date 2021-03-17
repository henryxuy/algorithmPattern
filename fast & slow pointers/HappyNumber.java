import java.util.*;

public class HappyNumber {
    public static boolean find(int num) {
        // happy number: keep replacing the number with the sum of squares of its digits -> a circle of 1
        // non-happy number: stuck in a circle which does not include 1.

        // double pointer: fast -> replace 2 steps, slow -> 1 step
        // if reach one -> true
        // if find a circle -> false
        int fast = num, slow = num;
        while(slow != 1 && fast != 1){
            fast = sumSquareDigits(sumSquareDigits(fast));
            slow = sumSquareDigits(slow);
            if(fast == slow){
                return false;
            }
        }

        return true;
    }

    public static boolean findAnswers(int num){
        // both happy and non-happy numbers would converge to a circle
        // happy nubmers converge to 1(single-element cycle). Non-happy numbers converge to a cycle not including 1.
        int fast = num, slow = num;
        do{
            fast = sumSquareDigits(sumSquareDigits(fast));
            slow = sumSquareDigits(slow);
        } while(fast != slow);

        return slow == 1;
    }


    private static int sumSquareDigits(int number){
        int result = 0;
        // List<Integer> digits = new ArrayList<>();
        while(number != 0){
            int tempDigit = number % 10;
            // digits.add(tempDigit);
            number = number / 10;
            result += tempDigit * tempDigit;
        }

        // for(Integer digit : digits){
        //     result += digit * digit;
        // }

        return result;
    }



    public static void main(String[] args) {
        System.out.println(HappyNumber.find(23));
        System.out.println(HappyNumber.find(12));
    }
    
}
