package algorithm.Ologn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/10/11 11:07
 */
public class LikeRedis {

    private int goods = 1;

    //线程池用于多线程处理任务
    private static ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20000));

    public void serverStart() throws Exception{ //接收客户端
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            /*这里没有使用生产者-消费者模型，没有使用队列来接收client，因为队列的put操作涉及到加锁解锁，更轻量级也是volatile的内存可见性相关操作
            * 而这里仅使用一个线程接收到请求后直接进行数量的判断，减少了加锁解锁等相关操作，为了提升accpet的速度，将响应结果放到任务队列中，由其他
            * 线程进行处理，这里也是为了提升accept后处理的速度，尽可能的减少accept线程的系统调用数量*/
            Socket client = serverSocket.accept();
            if(goods == 1) { //单线程操作，减少竞态的出现
               buildRespTask(client, "成功");
            } else {
                buildRespTask(client, "失败");
            }
        }
    }

    private Runnable buildRespTask(Socket client, String resp){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    client.getOutputStream().write(resp.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return runnable;
    }

}
