package io_nio.MyEasyNetty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by fengtingting on 2020/7/9.
 */
public class ReadWriteThread extends Thread{
    /*待处理的新连接*/
    private LinkedList<SocketChannel> socketList = new LinkedList<>();
    /*专门处理读写的selector，线程独有*/
    private Selector readWriteSelector;
    public ReadWriteThread() {
        try {
            readWriteSelector = Selector.open();
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加新的连接
     *
     * @param socketChannel 新的连接
     */
    public void addNewSocket(SocketChannel socketChannel){
        this.socketList.add(socketChannel);
    }
    @Override
    public void run(){
        System.out.println("readWrite thread start");
        while (true){
            try {
                if(socketList.size() > 0){
                    SocketChannel socketChannel = socketList.removeFirst();
                    socketChannel.register(readWriteSelector, SelectionKey.OP_READ);
                }
                int readyNum = readWriteSelector.select(100);
                if (readyNum > 0) {
                    Set<SelectionKey> keySet = readWriteSelector.selectedKeys();
                    Iterator<SelectionKey> iterator = keySet.iterator();
                    while (iterator.hasNext()){
                        SelectionKey readyKey = iterator.next();
                        if(readyKey.isReadable()){ /*读事件*/
                            SocketChannel socketChannel = (SocketChannel) readyKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                            int readNum = socketChannel.read(byteBuffer);
                            if(readNum >= 0){
                                System.out.println("read from "+socketChannel.getRemoteAddress()+" "+ new String(byteBuffer.array()));
                                socketChannel.register(readWriteSelector, SelectionKey.OP_WRITE);
                            } else {
                                /*读取到-1，说明client已经关闭了，服务端也要进行主动关闭，以免造成close_wait浪费文件描述符*/
                                System.out.println("read from "+socketChannel.getRemoteAddress()+" close");
                                socketChannel.close();
                            }

                        } else if (readyKey.isWritable()) { /*写事件*/
                            SocketChannel socketChannel = (SocketChannel) readyKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                            byteBuffer.put("hello I'm server".getBytes());
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);
                            /*写事件执行完毕后，要将对应的文件描述符从epoll的红黑树中移除，以免重复触发写事件*/
                            socketChannel.register(readWriteSelector, SelectionKey.OP_READ);
                        } else {
                            System.out.println("非读写事件发生了");
                        }
                        iterator.remove();
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
