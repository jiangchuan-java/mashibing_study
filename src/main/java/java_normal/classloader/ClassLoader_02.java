package java_normal.classloader;

/**
 * Created by fengtingting on 2020/7/12.
 */
public class ClassLoader_02 {

    public static void main(String[] args) {
        System.out.println(P.a);/*常量(javac 编译的时候就确定了值)，不需要加载类*/
        System.out.println(P.b);/*静态变量，加载类*/
    }

}


class P {
    public static final int a = 10;
    public static int b = 5;
    static {
        System.out.println("P");
    }
}