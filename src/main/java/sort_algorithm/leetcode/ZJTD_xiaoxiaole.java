package sort_algorithm.leetcode;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-5-12
 */
public class ZJTD_xiaoxiaole {

    public String clean(String str){
        int length = str.length();
        StringBuffer sb = new StringBuffer(str);

        int i=0;

        while (i<sb.length()){
            if(sb.charAt(i) == 'b'){
                sb.deleteCharAt(i);
                if(i > 0){
                    i--;
                }
            } else {
                if(sb.charAt(i) == 'a' && i+1 <= sb.length()-1 && sb.charAt(i+1) == 'c'){
                    sb.delete(i,i+2);
                    if(i > 0){
                        i--;
                    }
                } else {
                    i++;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "aaabbccc";
        System.out.println(new ZJTD_xiaoxiaole().clean(str));
    }
}
