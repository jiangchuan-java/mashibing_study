package java_normal.classloader;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-13
 */
public class ClassLoader_03 {
    static int a = 10;
    static {
        a++;
    }

    public static void main(String[] args) {
        System.out.println(a);
    }
}
