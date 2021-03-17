public class BackspaceCompare {

    public static boolean compare(String str1, String str2) {
        int firstLength = str1.length(), secondLength = str2.length();
        int left = firstLength - 1, right = secondLength - 1;

        while(left >= 0 && right >= 0){
            char firstChar = str1.charAt(left), secondChar = str2.charAt(right);
            // find all '#'s and skip
            if(firstChar == '#'){
                int backspaceCount = 1;
                while(left - 1 >= 0 && str1.charAt(left - 1) == '#'){
                    backspaceCount++;
                    left--;
                }
                left -= backspaceCount + 1;
                // update current char
                firstChar = str1.charAt(left);
            }
            if(secondChar == '#'){
                int backspaceCount = 1;
                while(right - 1 >= 0 && str2.charAt(right - 1) == '#'){
                    backspaceCount++;
                    right--;
                }
                right -= backspaceCount + 1;
                secondChar = str2.charAt(right);
            }
            // compare
            if(firstChar == secondChar){
                left--;
                right--;
            }
            else{
                return false;
            }
        }


        return left == right;
    }
    
}
