package java_.thread_lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by fengtingting on 2020/6/29.
 */
public class MyLock {

   List<Thread> blockingList = new ArrayList<>();

   private AtomicInteger state = new AtomicInteger(1);

   public boolean tryLock(){
        if(state.compareAndSet(1,0)){
            //拿到锁了
            return true;
        } else {
            //进入阻塞队列
            Thread thread = Thread.currentThread();
            return false;
        }
    }

    public void lock(){
        while (true){
            if(state.compareAndSet(1,0)){
                break;
            }
        }
        if(state.compareAndSet(1,0)){
            //拿到锁了
        } else {
            //进入阻塞队列
            Thread thread = Thread.currentThread();
            blockingList.add(thread);
            while (true){
                if(blockingList.get(0) == thread){ //队列中第一个
                    if(state.compareAndSet(1,0)){
                        break;
                    }
                }
            }
        }
    }
}
