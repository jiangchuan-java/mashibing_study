package io;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by fengtingting on 2020/7/6.
 */
public class Java_Nio {
    public static void main(String[] args) throws Exception{
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8090));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
}
