package sort_algorithm.leetcode;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 2020-05-04
 */
public class Leetcode_03 {

    public int lengthOfLongestSubstring(String s) {
        int l = 0;
        int r = 0;
        int maxLength = 0;
        Integer[] array = new Integer[256];

        while(l<r || r <= s.length()-1){
            char rc = s.charAt(r);
            if(array[Integer.valueOf(rc)] != null){
                char lc = s.charAt(l);
                array[Integer.valueOf(lc)] = null;
                l++;
            } else {
                array[Integer.valueOf(rc)] = Integer.valueOf(rc);
                int length = r - l + 1;
                if(length > maxLength){
                    maxLength = length;
                }
                if(r < s.length()-1){
                    r++;
                } else {
                    break;
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode_03().lengthOfLongestSubstring(" "));
    }
}
