package java_.refence;

import java.lang.ref.SoftReference;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-1
 */
public class SoftRefence_01 {

    public static void main(String[] args) {
        SoftReference<byte[]> data = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(data.get());

        System.gc(); //gc后堆内存还够，软引用还在

        System.out.println(data.get());

        byte[] data2 = new byte[1024*1024*15];

        System.out.println(data.get()); //堆内存不足，gc回收就会将软引用干掉
    }
}
