package java_normal.io_nio.select_poll_epoll;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Des: 多路复用器，探索底层系统调用
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-7
 */
public class Multiplexer {

    public static void main(String[] args) throws Exception{
        Selector selector = Selector.open(); //多路复用器

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",9090));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int readyKeys = selector.select(500);
            if(readyKeys > 0){
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keySet.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isAcceptable()){ //可连接
                        ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverSocket.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("accept from "+socketChannel.getRemoteAddress());
                    } else if(key.isReadable()){ //可读
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        int readNum = socketChannel.read(byteBuffer);
                        if(readNum >= 0){
                            System.out.println("read from "+socketChannel.getRemoteAddress()+" "+ new String(byteBuffer.array()));
                            byteBuffer.clear();
                            byteBuffer.put("I'm server".getBytes());
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);
                        } else if(readNum < 0){
                            System.out.println("read from "+socketChannel.getRemoteAddress()+" close");
                            socketChannel.close();
                        }

                    }
                    iterator.remove();
                }
            }
        }

    }
}
