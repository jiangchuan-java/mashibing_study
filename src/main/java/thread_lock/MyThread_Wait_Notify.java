package thread_lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by fengtingting on 2020/6/15.
 */
public class MyThread_Wait_Notify {


    public static void main(String[] args) throws Exception {

        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        for (int i = 1; i <= 100; i++) {
                            System.out.println(Thread.currentThread().getName()+": "+i);
                            TimeUnit.SECONDS.sleep(1);
                            lock.notify();
                            lock.wait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        for (int i = 0; i < 26; i++) {
                            char letter = (char)('a'+i);
                            System.out.println(Thread.currentThread().getName()+": "+letter);
                            TimeUnit.SECONDS.sleep(1);
                            lock.notify();
                            lock.wait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();

    }


}
