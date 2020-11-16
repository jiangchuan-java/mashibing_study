package java_normal.io_nio.MyJavaNio;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;


/**
 * 自己实现的一个简单的Netty服务端，基于javaNio
 * Created by fengtingting on 2020/7/9.
 */
public class JavaNioServer {

    private static SelectorThread readWriteThread = new SelectorThread();

    public void accept() throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9090));

        SelectorTheadGroup bossGroup = new SelectorTheadGroup(1);
        SelectorTheadGroup childGroup = new SelectorTheadGroup(5);
        bossGroup.setChildGroup(childGroup);
        bossGroup.register(serverSocketChannel);
    }

    public static void main(String[] args) throws Exception {
            JavaNioServer easyNettyServer = new JavaNioServer();
            easyNettyServer.accept();
    }

}
