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
            int readyNum = selector.select(100);
            if(readyNum > 0){
                doReadyEvent(selector);
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
                client.configureBlocking(false);
                client.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                SocketChannel client = (SocketChannel) selectionKey.channel();
                client.configureBlocking(false);
                /*File file = new File("src/main/java/servlet_container/tomcat/index.html");
                byte[] bytes = fileConvertToByteArray(file);*/
                ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                byteBuffer.put("hello".getBytes());
                client.write(byteBuffer);
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
