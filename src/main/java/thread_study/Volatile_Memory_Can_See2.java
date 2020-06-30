package thread_study;

import java.io.File;
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
    static boolean running = true;
    static List<Integer> list = new LinkedList<>();

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
                        list.add(1);
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
