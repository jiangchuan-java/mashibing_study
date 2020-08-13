package servlet_container.tomcat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 实现一个tomcat服务，可以进行网络通信,使用java-nio
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/8/13 23:23
 */
public class TomcatServer {

    public void start() throws Exception{
        init();
    }

    public void init() throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8080));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            try{
                int readyNum = selector.select(100);
                if(readyNum > 0){
                    doReadyEvent(selector);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private void doReadyEvent(Selector selector) throws Exception{
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
            SelectionKey selectionKey = iterator.next();
            if(selectionKey.isAcceptable()){
                ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                SocketChannel client = server.accept();
                System.out.println("accept from "+client.getRemoteAddress());
                client.configureBlocking(false);
                client.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                SocketChannel clientChanel = (SocketChannel) selectionKey.channel();
                clientChanel.configureBlocking(false);
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readNum = clientChanel.read(byteBuffer);
                if(readNum >= 0){
                    System.out.println("read from "+clientChanel.getRemoteAddress()+" "+ new String(byteBuffer.array()));
                    clientChanel.register(selector, SelectionKey.OP_WRITE);
                } else {
                    //读取到-1，说明client已经关闭了，服务端也要进行主动关闭，以免造成close_wait浪费文件描述符
                    System.out.println("read from "+clientChanel.getRemoteAddress()+" close");
                    clientChanel.close();
                }
            } else if (selectionKey.isWritable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                File file = new File("src/main/java/servlet_container/tomcat/index.html");
                byte[] bytes = fileConvertToByteArray(file);
                ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
                byteBuffer.put(bytes);
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                //写事件执行完毕后，要将对应的文件描述符从epoll的红黑树中移除，以免重复触发写事件
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else {
                System.out.println("非读写事件发生了");
            }
            iterator.remove();
        }
    }

    private byte[] fileConvertToByteArray(File file) {
        byte[] data = null;

        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[102400];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray();

            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    //main方法启动tomcat，访问8080端口可以出猫的页面
    public static void main(String[] args) throws Exception{
        TomcatServer tomcat = new TomcatServer();
        tomcat.start();
    }


}
