package sort_algorithm.leetcode;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 2020-05-03
 */
public class Leetcode_125 {

    private boolean checkChar(char c){
        int b = (int)c;
        if(b >= 97 && b <=122){
            return true;
        }

        if(b >= 65 && b <=90){
            return true;
        }

        if(b >= 48 && b <=57){
            return true;
        }
        return false;
    }
    public boolean isPalindrome(String s) {
        s = s.trim();
        //a-z:97-122,A-Z:65-90,0-9:48-57
        int left = 0;
        int right = s.length()-1;
        while(left < right){
            char l = s.charAt(left);
            char r = s.charAt(right);
            if(!checkChar(l)){
                left++;
            } else if(!checkChar(r)){
                right--;
            } else {
                boolean res = String.valueOf(l).equalsIgnoreCase(String.valueOf(r));
                if( l != r && !res){
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        boolean result = new Leetcode_125().isPalindrome(str);
        System.out.println(result);
    }
}
