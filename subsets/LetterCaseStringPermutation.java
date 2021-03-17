import java.util.*;

class LetterCaseStringPermutation {

    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        int N = str.length();
        permutations.add(str);

        for(int i = 0; i < N; i++){
            if(!Character.isLetter(str.charAt(i))){
                continue;
            }
            // filp the char at i
            char charChanged = str.charAt(i);
            if(Character.isUpperCase(charChanged)){
                charChanged = Character.toLowerCase(charChanged);
            }
            else{
                charChanged = Character.toUpperCase(charChanged);
            }
            int numberPermutations = permutations.size();
            for(int permutationIndex = 0; permutationIndex < numberPermutations; permutationIndex++){
                String tempStr = permutations.get(permutationIndex);
                StringBuilder tempStringBuilder = new StringBuilder(tempStr);
                tempStringBuilder.setCharAt(i, charChanged);
                permutations.add(tempStringBuilder.toString());
            }
        }

        return permutations;
    }


    public static void main(String[] args) {
        List<String> result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ad52");
        System.out.println(" String permutations are: " + result);

        result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);
    }
}