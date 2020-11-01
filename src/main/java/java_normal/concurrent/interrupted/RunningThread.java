package java_normal.concurrent.interrupted;

import java.util.concurrent.TimeUnit;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-9
 */
public class RunningThread extends Thread{

    @Override
    public void run(){
        while (true) {
            if(this.isInterrupted() || Thread.interrupted()){
                System.out.println("receive interrupted");
                break;
            } else {
                System.out.println("run");
            }
        }
    }

    public static void main(String[] args) throws Exception{
        RunningThread runningThread = new RunningThread();
        runningThread.start();
        TimeUnit.SECONDS.sleep(2);
        runningThread.interrupt();
    }

}
