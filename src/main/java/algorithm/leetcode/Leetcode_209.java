package algorithm.leetcode;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 2020-05-03
 */
public class Leetcode_209 {
    public int minSubArrayLen(int s, int[] nums) {
        int i = 0;
        int j = 0;
        int minLength = nums.length;
        //i等于j，且j到达末尾了就退出循环
        while( i < j || j < nums.length-1){
            int sum = 0;
            if(i==j){
                sum = nums[i];
            } else {
                for(int index = j; index >=i; index--){
                    sum = sum + nums[index];
                }
            }
            if(sum >= s){
                int subLength = j - i + 1;
                if(subLength < minLength){
                    minLength = subLength;
                }
                i++;
            } else {
                if(j == nums.length-1){
                    break;
                }
                j++;
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        int nums[] = new int[]{2,3,1,2,4,3};
        int minLen = new Leetcode_209().minSubArrayLen(7,nums);
        System.out.println(minLen);
    }
}
