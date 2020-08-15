package io_nio.MyEasyNetty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fengtingting on 2020/7/9.
 */
public class SelectorThread extends Thread{

    private static AtomicInteger theadNum = new AtomicInteger();
    /*待处理的新连接*/
    private LinkedList<SelectableChannel> socketList = new LinkedList<>();
    /*专门处理读写的selector，线程独有*/
    private Selector selector;

    public SelectorThread() {
        try {
            selector = Selector.open();
            this.setName("javaNio-selector-"+theadNum.incrementAndGet());
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加新的连接
     *
     * @param channel 新的连接
     */
    public void register(SelectableChannel channel){
        this.socketList.add(channel);
    }
    @Override
    public void run(){

        while (true){
            try {
                int readyNum = selector.select(100); //阻塞100毫秒，其他时间处理task
                if (readyNum > 0) {
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keySet.iterator();
                    while (iterator.hasNext()){
                        SelectionKey readyKey = iterator.next();
                        /*连接事件*/
                        if(readyKey.isAcceptable()){
                            doAcceptHandler(readyKey);
                        }
                        /*读事件*/
                        if(readyKey.isReadable()){
                            doReadHandler(readyKey);

                        } /*写事件*/
                        else if (readyKey.isWritable()) {
                            doWriteHandler(readyKey);

                        } else {
                            System.out.println("非读写事件发生了");
                        }
                        iterator.remove();
                    }
                }
                //do task
                if(socketList.size() > 0){
                    SelectableChannel channel = socketList.removeFirst();
                    if(channel instanceof ServerSocketChannel){
                        ServerSocketChannel server = (ServerSocketChannel) channel;
                        server.register(selector, SelectionKey.OP_ACCEPT);
                    } else if (channel instanceof  SocketChannel) {
                        SocketChannel client = (SocketChannel) channel;
                        client.register(selector, SelectionKey.OP_READ);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }



    private void doAcceptHandler(SelectionKey readyKey) throws Exception{
        ServerSocketChannel serverSocket = (ServerSocketChannel) readyKey.channel();
        SocketChannel client = serverSocket.accept();
        System.out.println("accept from "+client.getRemoteAddress());
        client.configureBlocking(false);
    }

    private void doReadHandler(SelectionKey readyKey) throws Exception{
        SocketChannel socketChannel = (SocketChannel) readyKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        int readNum = socketChannel.read(byteBuffer);
        if(readNum >= 0){
            System.out.println("read from "+socketChannel.getRemoteAddress()+" "+ new String(byteBuffer.array()));
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        } else {
            /*读取到-1，说明client已经关闭了，服务端也要进行主动关闭，以免造成close_wait浪费文件描述符*/
            System.out.println("read from "+socketChannel.getRemoteAddress()+" close");
            socketChannel.close();
            readyKey.cancel(); //从多路复用器中移除
        }
    }

    private void doWriteHandler(SelectionKey readyKey) throws Exception{
        SocketChannel socketChannel = (SocketChannel) readyKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        byteBuffer.put("hello I'm server".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        /*写事件执行完毕后，要将对应的文件描述符从epoll的红黑树中移除，以免重复触发写事件*/
        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}
