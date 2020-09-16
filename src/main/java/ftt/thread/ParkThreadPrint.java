package ftt.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/9/14 21:46
 */
public class ParkThreadPrint extends Thread{

    private Thread unParkThread;
    private Runnable runnable;


    @Override
    public void run(){
        this.runnable.run();
    }

    public void setRunnable(Runnable runnable){
        this.runnable = runnable;
    }

    public void setUnPark(Thread unParkThread) {
        this.unParkThread = unParkThread;
    }

    public Thread getUnParkThread() {
        return unParkThread;
    }

    public void setUnParkThread(Thread unParkThread) {
        this.unParkThread = unParkThread;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public static void main(String[] args) {
        ParkThreadPrint t1 = new ParkThreadPrint();
        ParkThreadPrint t2 = new ParkThreadPrint();
        ParkThreadPrint t3 = new ParkThreadPrint();

        t1.setUnPark(t2);
        t1.setRunnable(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                for(int i=1;i<=26;i++){
                    System.out.println(i);
                    LockSupport.unpark(t1.getUnParkThread());
                    LockSupport.park();
                }
            }
        });

        t2.setUnPark(t3);
        t2.setRunnable(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                for(int i=65;i<=90;i++){
                    System.out.println((char)i);
                    LockSupport.unpark(t2.getUnParkThread());
                    LockSupport.park();
                }
            }
        });

        t3.setUnPark(t1);
        t3.setRunnable(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                for(int i=97;i<=112;i++){
                    System.out.println((char)i);
                    LockSupport.unpark(t3.getUnParkThread());
                    LockSupport.park();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }
}
