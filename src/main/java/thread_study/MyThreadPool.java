package thread_study;

import java.util.concurrent.*;

/**
 * Created by fengtingting on 2020/6/15.
 */
public class MyThreadPool {

    public static void main(String[] args) throws Exception{

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,
                100,
                30, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(Integer.MAX_VALUE));




        ExecutorService cachePool = Executors.newCachedThreadPool();//最小0，最大无限，用了一个同步的队列，插入的时候，必失败,创建新线程

        ThreadPoolExecutor cachepool = new ThreadPoolExecutor(
                0,
                Integer.MAX_VALUE,
                1, TimeUnit.MINUTES,
                new SynchronousQueue<>());

        ExecutorService fixedPool = Executors.newFixedThreadPool(50); //不能扩充
        ThreadPoolExecutor fixepool = new ThreadPoolExecutor(
                5,
                5,
                0, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(5));


        for(int i=0;i<100;i++){
            fixepool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            System.out.println(i);
        }
    }
}
