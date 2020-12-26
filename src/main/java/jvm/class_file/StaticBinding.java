package jvm.class_file;

/**
 * @Author: jiangchuan
 * <p>
 * @Date: 20-12-7
 */
public class StaticBinding {

    public static void main(String[] args) {
        Father father = new Son();
        Father father1 = new Father();
        father.f2();
    }
}
