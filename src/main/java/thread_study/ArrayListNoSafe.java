package thread_study;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by fengtingting on 2020/6/22.
 */
public class ArrayListNoSafe {

    public static void main(String[] args) throws Exception{
        ArrayList<String> list = new ArrayList<>(3000000);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    list.add("a");
                }
                countDownLatch.countDown();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    if(list.size() > 0){
                        list.get(0);
                    }
                }
                countDownLatch.countDown();
            }
        });
        t1.start();
        t2.start();
        countDownLatch.await();
        System.out.println(list.size());

    }
}


