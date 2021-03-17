import java.util.*;

class GeneralizedAbbreviation {

    public static List<String> generateGeneralizedAbbreviation(String word) {
        List<String> result = new ArrayList<String>();
        int wordLen = word.length();
        
        Queue<AbbreviatedWord> queue = new LinkedList<>();
        queue.add(new AbbreviatedWord(new StringBuilder(), 0, 0));
        while(!queue.isEmpty()){
            AbbreviatedWord abWord = queue.poll();
            if(abWord.start == wordLen){
                // final skip
                if(abWord.count != 0){
                    abWord.str.append(abWord.count);
                }
                result.add(abWord.str.toString());
            }
            else{
                // abbreviate
                queue.offer(new AbbreviatedWord(new StringBuilder(abWord.str), abWord.start + 1, abWord.count + 1));

                // skip, reset and add the count
                if(abWord.count != 0){
                    abWord.str.append(abWord.count);
                }
                queue.offer(new AbbreviatedWord(new StringBuilder(abWord.str).append(word.charAt(abWord.start)), abWord.start + 1, 0));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> result = GeneralizedAbbreviation.generateGeneralizedAbbreviation("BAT");
        System.out.println("Generalized abbreviation are: " + result);

        result = GeneralizedAbbreviation.generateGeneralizedAbbreviation("code");
        System.out.println("Generalized abbreviation are: " + result);
    }
}

class AbbreviatedWord {
    StringBuilder str;
    int start;
    int count;
  
    public AbbreviatedWord(StringBuilder str, int start, int count) {
      this.str = str;
      this.start = start;
      this.count = count;
    }
  }
  
