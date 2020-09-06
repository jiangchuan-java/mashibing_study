package java_.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/9/5 16:17
 */
public class LinkedBlockingQueue_01 {

    public static void main(String[] args) throws Exception{
        LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
        Runnable task = new Runnable() {
            @Override
            public void run() {

            }
        };

        //remove for blocking
        taskQueue.take();
        //add for blocking
        taskQueue.put(task);
        //add -> true or false -> no blocking
        taskQueue.offer(task);

        //remove for blocking
        taskQueue.take();
        //remove -> Object or null -> no blocking
        taskQueue.poll();

    }
}
