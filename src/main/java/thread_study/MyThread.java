package thread_study;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fengtingting on 2020/6/15.
 */
public class MyThread extends Thread {


    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }


    public static void main(String[] args) throws Exception {
        MyThread thread = new MyThread();
        System.out.println(thread.getState());

        thread.start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(thread.getState());

        }
        thread.join();
        System.out.println(thread.getState());

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndIncrement();
        boolean res = atomicInteger.compareAndSet(0,1);
    }


}
