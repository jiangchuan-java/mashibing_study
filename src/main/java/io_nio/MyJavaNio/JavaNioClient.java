package io_nio.MyJavaNio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class JavaNioClient {

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
                    /*已连接事件*/
                    if(selectionKey.isConnectable()){
                        SocketChannel clientChanel = (SocketChannel) selectionKey.channel();
                        clientChanel.configureBlocking(false);
                        clientChanel.register(selector, SelectionKey.OP_WRITE);
                    }
                    /*已连接事件*/
                    else if (selectionKey.isWritable()){
                        SocketChannel clientChanel = (SocketChannel) selectionKey.channel();
                        clientChanel.configureBlocking(false);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                        byteBuffer.put("hello server".getBytes());
                        byteBuffer.flip();
                        clientChanel.write(byteBuffer);
                        clientChanel.register(selector, SelectionKey.OP_READ);
                    }
                    /*可读事件*/
                    else if (selectionKey.isReadable()){
                        SocketChannel clientChanel = (SocketChannel) selectionKey.channel();
                        clientChanel.configureBlocking(false);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                        int readNum = socketChannel.read(byteBuffer);
                        if(readNum >= 0){
                            System.out.println("read from "+socketChannel.getRemoteAddress()+" "+ new String(byteBuffer.array()));
                            socketChannel.close();
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
        for(int i = 0; i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JavaNioClient easyNettyClient = new JavaNioClient();
                        easyNettyClient.connect();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
