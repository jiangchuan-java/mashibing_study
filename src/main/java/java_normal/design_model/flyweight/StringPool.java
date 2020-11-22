package java_normal.design_model.flyweight;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/22 15:59
 */
public class StringPool {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");

        System.out.println(s1 == s2); //true
        System.out.println(s1 == s3); //false
        System.out.println(s3 == s4); //false
        System.out.println(s3.intern() == s1); //true
        System.out.println(s3.intern() == s4.intern()); //true
    }
}
