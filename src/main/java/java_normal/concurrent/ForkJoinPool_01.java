package java_normal.concurrent;

import java.nio.ByteBuffer;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/8/30 12:56
 */
public class ForkJoinPool_01 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer = ByteBuffer.allocateDirect(10);
    }
}
