class NextLetter {

    public static char searchNextLetter(char[] letters, char key) {
        // find the smallest letter that is greater than (>) key
        // suppose the array is circular
        if(key < letters[0]){
            return letters[0];
        }
        if(key >= letters[letters.length - 1]){
            return letters[0];
        }
        // the result is in the array, use binary search
        int left = 0, right = letters.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(letters[mid] == key){
                return letters[mid + 1];
            }
            else if(letters[mid] < key){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }

        return letters[left];
    }
  
    public static void main(String[] args) {
        System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'f'));
        System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'b'));
        System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'm'));
        System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'h'));
    }
  }