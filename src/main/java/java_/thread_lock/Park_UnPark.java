package java_.thread_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by fengtingting on 2020/7/11.
 */
public class Park_UnPark {
    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("first park");
                LockSupport.park();
                System.out.println("second park");
                LockSupport.park();
                System.out.println("all unPark");
            }
        });

        t1.start();

        /*多次unpark也没用，unpark仅是将许可设置为1，并不是累加，许可使用后变成0，
        两次unpark都是将许可变成1,第二次如果延迟发放许可是可以的*/
        LockSupport.unpark(t1);

        TimeUnit.SECONDS.sleep(2);
        LockSupport.unpark(t1);
    }
}
