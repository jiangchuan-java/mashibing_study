package io_nio.MyEasyNetty;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


/**
 * 自己实现的一个简单的Netty服务端，基于javaNio
 * Created by fengtingting on 2020/7/9.
 */
public class EasyNettyServer {

    public static void main(String[] args) throws Exception {
        Selector acceptSelector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9090));
        serverSocketChannel.register(acceptSelector, SelectionKey.OP_ACCEPT);

        while (true) {
            int readyNum = acceptSelector.select(100);
            if (readyNum > 0) {
                Iterator<SelectionKey> iterator = acceptSelector.keys().iterator();
                while (iterator.hasNext()){
                    SelectionKey readyKey = iterator.next();
                    ServerSocketChannel serverSocket = (ServerSocketChannel) readyKey.channel();
                    SocketChannel socketChannel = serverSocket.accept();
                    socketChannel.configureBlocking(false);

                    iterator.remove();
                }
            }
        }

    }

}
