package thread_study;

import sun.misc.Contended;
import sun.misc.Unsafe;

import java.io.File;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fengtingting on 2020/6/30.
 */
public class Volatile_Memory_Can_See2 {

    static volatile int a = 0;
    @Contended
    static boolean running = true;
    @Contended
    private static final Unsafe unsafe = getUnsafe();
    @Contended
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (Volatile_Memory_Can_See1.class.getDeclaredField("state"));
        } catch (Exception ex) { throw new Error(ex); }
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

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
               try {
                   TimeUnit.SECONDS.sleep(3);
                   running = false;
                   System.out.println(Thread.currentThread().getName()+" 停止运行");
               }catch (Exception e){

               }
            }

        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" 开始运行");
                    while (running) {
                        unsafe.compareAndSwapInt(this,valueOffset,0,1);//执行cas操作，触发lock前缀，验证Lock是否有内存屏障
                        //Socket socket = new Socket(); -> 立即刷新
                        //File file = new File("");  -> 立即刷新
                    }
                    System.out.println(Thread.currentThread().getName()+" 停止运行");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t2.start();
        t1.start();
        t1.join();
        t2.join();
        //System.out.println(num);
    }
}
