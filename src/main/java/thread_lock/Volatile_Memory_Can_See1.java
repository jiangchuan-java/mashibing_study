package thread_lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by fengtingting on 2020/6/29.
 * 读屏障：在指令 前 插入 Load Barrier，可以让 高速缓存中的 数据失效，
 * 强制从新从主内存加载数据。强制读取主内存内容，让CPU缓存与主内存保持一致，避免了缓存导致的一致性问题。
 * <p>
 * 写屏障：在指令插入 Store Barrier，能让写入 内存中 的最新数据 更新 写入主 内存，让其他线程可见。
 * 强制写入主内存，这种显示调用，CPU就不会因为性能考虑而去对指令重排。
 */

public class Volatile_Memory_Can_See1 extends AbstractQueuedSynchronizer {
    private static final Unsafe unsafe = getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (Volatile_Memory_Can_See1.class.getDeclaredField("state"));
        } catch (Exception ex) { throw new Error(ex); }
    }
    volatile int state = 0;
    int sum = 0;


    public static void main(String[] args) throws Exception{
        Volatile_Memory_Can_See1 reentrantLock = new Volatile_Memory_Can_See1();
        Thread t1 = new Thread(reentrantLock::doAdd);
        Thread t2 = new Thread(reentrantLock::doAdd);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(reentrantLock.sum);
    }

    private void doAdd() {
        int s;
        for (int i = 0; i < 100000; i++) {
            while (true){
                s = state;
                if (unsafe.compareAndSwapInt(this,valueOffset,0,1)) {
                    sum++;
                    state = 0;
                    break;
                }
            }

        }
    }

    private static Unsafe getUnsafe() {
        Field singleoneInstanceField = null;
        try {
            singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            return (Unsafe) singleoneInstanceField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}


