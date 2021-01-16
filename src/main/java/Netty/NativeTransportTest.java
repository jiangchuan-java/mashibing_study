package Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.util.concurrent.FastThreadLocal;
import org.junit.Test;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/19 21:41
 */
public class NativeTransportTest {

    @Test
    public void threadLocalTest(){
        new FastThreadLocal<>().get();
        new ThreadLocal<>().get();
        PooledByteBufAllocator pooledByteBufAllocator = new PooledByteBufAllocator();
        ByteBuf byteBuf = pooledByteBufAllocator.directBuffer(957); //对象池 + 内存池
        ByteBuf byteBuf2 = pooledByteBufAllocator.directBuffer(600); //对象池 + 内存池
        ByteBuf byteBuf3 = pooledByteBufAllocator.directBuffer(795); //对象池 + 内存池

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(990);
        byteBuffer.get(new byte[1024]);

    }
}
