package thread_study;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by fengtingting on 2020/6/29.
 */
public class MyLock {
    private Sync sync;

    public MyLock(){
        sync = new Sync();
    }

    public void lock(){
        sync.acquire(1);
    }

    public void unlock(){
        sync.release(1);
    }

    private static class Sync extends AbstractQueuedSynchronizer{
        private volatile int state = 0;

        protected  boolean tryAcquire(int acquires) {
            int c = getState();
            if (c == 0) {
                return compareAndSetState(0, acquires);
            }
            return false;
        }

        protected  boolean tryRelease(int releases) {
            int c = getState() - releases;
            boolean free = false;
            if (c == 0) {
                free = true;
            }
            setState(c);
            return free;
        }
    }
}
