package java_.thread_lock;

import java.util.concurrent.locks.LockSupport;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/9/5 23:31
 */
public class Locksupport_01 {
    public static void main(String[] args) {
        LockSupport.park(new Locksupport_01());
        LockSupport.unpark(Thread.currentThread());
    }
}
