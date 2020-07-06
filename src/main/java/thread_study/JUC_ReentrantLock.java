package thread_study;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fengtingting on 2020/6/27.
 */
public class JUC_ReentrantLock extends Thread{

    static ReentrantLock reentrantLock = new ReentrantLock(true);
    static Condition condition = reentrantLock.newCondition();

    @Override
    public void run(){
        try {
            for(int i=0;i<100;i++){
                reentrantLock.lock();
                condition.signal();
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"__获取锁");
                reentrantLock.unlock();
                //condition.await(); //效果等同于释放锁
            }
        }catch (Exception e){

        }

    }

    public static void main(String[] args) {
        JUC_ReentrantLock thread1 = new JUC_ReentrantLock();
        JUC_ReentrantLock thread2 = new JUC_ReentrantLock();
        thread1.start();
        thread2.start();
    }
}
