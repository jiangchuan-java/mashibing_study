package thread_lock;

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

        pool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });


        /*无线扩容*/
        ExecutorService cachePool = Executors.newCachedThreadPool();

        cachePool = new ThreadPoolExecutor(
                0,
                Integer.MAX_VALUE,
                1, TimeUnit.MINUTES,
                new SynchronousQueue<>());

        /*大小固定*/
        ExecutorService fixedPool = Executors.newFixedThreadPool(50);
        fixedPool = new ThreadPoolExecutor(
                5,
                5,
                0, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(5));

    }
}
