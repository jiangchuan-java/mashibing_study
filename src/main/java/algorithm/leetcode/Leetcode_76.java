package algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 2020-05-04
 */
public class Leetcode_76 {

    public String minWindow(String s, String t) {
        int l = 0;
        int r = 0;
        int minLength = 0;
        String minStr = "";
        int matchCount = 0; //已经匹配上的个数，当匹配数等于t.length()说明全部包含了
        Map<Character, Integer> unMatchLetterMap = new HashMap<Character, Integer>(); //待匹配的字符以及待匹配的次数
        Map<Character, Integer> replicaLetterMap = new HashMap<Character, Integer>(); //已经匹配的这一段子串中，重复匹配的字符是哪些，以及重复的次数是多少
        for(int i=0;i<t.length();i++){
            char c = t.charAt(i);
            Integer num = unMatchLetterMap.get(c);
            if(num == null){
                unMatchLetterMap.put(c, 1);
            } else {
                num++;
                unMatchLetterMap.put(c, num);
            }
        }
        while(l < s.length()){
            if(matchCount < t.length()){
                char rc = s.charAt(r);
                Integer num = unMatchLetterMap.get(rc);
                if(num !=null){ // 包含目标串中的字符
                    if(num > 0){
                        matchCount++;
                        num--;
                        unMatchLetterMap.put(rc, num);
                    } else {//重复匹配了，记录重复的字符以及重复的次数
                        Integer replicaNum = replicaLetterMap.get(rc);
                        if(replicaNum == null){
                            replicaLetterMap.put(rc, 1);
                        } else {
                            replicaNum++;
                            replicaLetterMap.put(rc, replicaNum);
                        }
                    }
                }
            }
            if(matchCount < t.length()){ //还没有匹配上全部的目前字符，窗口向右移动
                if(r < s.length()-1){
                    r++;
                } else {
                    break;
                }
            } else { //匹配上全部字符，窗口逐步向右移动，寻找最小的子串
                int matchLength = r-l+1;
                if(minLength == 0){
                    minLength = matchLength;
                    minStr = s.substring(l,r+1);
                } else if (matchLength < minLength){
                    minLength = matchLength;
                    minStr = s.substring(l,r+1);
                }
                char lc = s.charAt(l); //移动前先判断下是否影响了整体的匹配结果，如果有影响，移动完左侧后，需要继续移动右侧窗口找到再次找到一个完全包含的子串。
                if(unMatchLetterMap.containsKey(lc)){
                    Integer replicaNum = replicaLetterMap.get(lc);
                    if(replicaNum != null && replicaNum > 0){
                        replicaNum--;
                        replicaLetterMap.put(lc, replicaNum);
                    } else {
                        matchCount--;
                        unMatchLetterMap.put(lc, unMatchLetterMap.get(lc)+1); //待匹配的字符对应数量+1,因为移动左侧窗口时减少了一个.
                        if(r < s.length()-1){
                            r++;
                        } else {
                            break;
                        }
                    }
                }
                l++; //左侧窗口开始向右移动
            }
        }
        return minStr;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(new Leetcode_76().minWindow(s,t));
    }
}
