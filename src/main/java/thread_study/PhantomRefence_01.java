package thread_study;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-1
 */
public class PhantomRefence_01 {

    private static ReferenceQueue<Integer> referenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(1);
        PhantomReference<Integer> phantomReference = new PhantomReference<>(new Integer(1), referenceQueue);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Reference<? extends Integer> reference = referenceQueue.poll();
                        if(Objects.nonNull(reference)){
                            System.out.println("gc发生后，虚引用放入了队列中");
                        }
                        TimeUnit.SECONDS.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(3);
        System.gc();

        countDownLatch.await();

    }
}
