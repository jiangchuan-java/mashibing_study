package io_nio.MyEasyNetty;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * 自己实现的一个简单的Netty服务端，基于javaNio
 * Created by fengtingting on 2020/7/9.
 */
public class JavaNioServer {

    private static SelectorThread readWriteThread = new SelectorThread();

    public void accept() throws Exception{
        Selector acceptSelector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9090));
        serverSocketChannel.register(acceptSelector, SelectionKey.OP_ACCEPT);

        System.out.println("server start");

        while (true) {
            int readyNum = acceptSelector.select(); //阻塞 直到有连接
            if (readyNum > 0) {
                Set<SelectionKey> keySet = acceptSelector.selectedKeys();
                acceptSelector.wakeup();
                Iterator<SelectionKey> iterator = keySet.iterator();
                while (iterator.hasNext()){
                    SelectionKey readyKey = iterator.next();

                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
            JavaNioServer easyNettyServer = new JavaNioServer();
            easyNettyServer.accept();
    }

}
