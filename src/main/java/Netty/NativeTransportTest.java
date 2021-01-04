package Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.util.concurrent.FastThreadLocal;
import org.junit.Test;

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
        ByteBuf byteBuf = pooledByteBufAllocator.directBuffer(1024); //对象池 + 内存池
        System.out.println(byteBuf);
    }
}
