package java_.thread_lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by fengtingting on 2020/6/27.
 */
public class JUC_Semaphore extends Thread{

    static Semaphore semaphore = new Semaphore(1, true);

    @Override
    public void run(){
        try {
            for(int i=0;i<100;i++){
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"__获取信号");
                semaphore.release();
            }
        }catch (Exception e){

        }

    }

    public static void main(String[] args) {
        JUC_Semaphore thread1 = new JUC_Semaphore();
        JUC_Semaphore thread2 = new JUC_Semaphore();
        thread1.start();
        thread2.start();
    }
}
