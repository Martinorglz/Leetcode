import java.util.ArrayList;

public class LongestSubstr {
    public static int findTheLongestSubstr(String str){
        int maxLength = 0;

        // split string into substrings
        for (int i = 0; i<=str.length(); i++){
            for (int j = 0; j<=str.length(); j++){
                boolean noRepeat = true;
                if (i+j > str.length()){
                    continue;
                }
                String substring = str.substring(i,i+j);
                for (int k = 0; k < substring.length() - 1; k++){
                    char character = substring.charAt(k);
                    if (substring.lastIndexOf(character) != k) {
                        noRepeat = false;
                        break;
                    }
                }
                if (noRepeat){
                    if (substring.length() > maxLength){
                        maxLength = substring.length();
                    }
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(findTheLongestSubstr("aabacdea"));
    }
}
