package java_normal.refence;

import java.lang.ref.WeakReference;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-1
 */
public class WeakRefence_01 {

    public static void main(String[] args) {
        WeakReference<byte[]> data = new WeakReference<>(new byte[1024*1024*10]);
        System.out.println(data.get());

        System.gc(); //gc后弱引用被回收

        System.out.println(data.get());
    }
}
