package io_nio.MyEasyNetty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EasyNettyClient {

    public void connect() throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        boolean connect = socketChannel.connect(new InetSocketAddress("127.0.0.1",9090));
        socketChannel.finishConnect();
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_WRITE);
        while (true){
            int nums = selector.select();//阻塞 直到可写
            System.out.println(nums);
            if(nums > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isConnectable()){
                        SocketChannel clientChanel = (SocketChannel) selectionKey.channel();
                        clientChanel.configureBlocking(false);
                        clientChanel.register(selector, SelectionKey.OP_WRITE);
                    } else if (selectionKey.isWritable()){
                        SocketChannel clientChanel = (SocketChannel) selectionKey.channel();
                        clientChanel.configureBlocking(false);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                        byteBuffer.put("hello server".getBytes());
                        byteBuffer.flip();
                        clientChanel.write(byteBuffer);
                        clientChanel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()){
                        SocketChannel clientChanel = (SocketChannel) selectionKey.channel();
                        clientChanel.configureBlocking(false);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                        int readNum = socketChannel.read(byteBuffer);
                        if(readNum >= 0){
                            System.out.println("read from "+socketChannel.getRemoteAddress()+" "+ new String(byteBuffer.array()));
                            socketChannel.close();
                            System.exit(99);
                        } else {
                            /*读取到-1，说明client已经关闭了，服务端也要进行主动关闭，以免造成close_wait浪费文件描述符*/
                            System.out.println("read from "+socketChannel.getRemoteAddress()+" close");
                            socketChannel.close();
                        }
                    }
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        EasyNettyClient easyNettyClient = new EasyNettyClient();
        easyNettyClient.connect();
    }
}
