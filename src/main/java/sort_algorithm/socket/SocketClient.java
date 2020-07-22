package sort_algorithm.socket;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-5-18
 */
public class SocketClient {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket();
        socket.setReceiveBufferSize(1);
        socket.connect(new InetSocketAddress("127.0.0.1", 9600));
        socket.getOutputStream().write("hello".getBytes());
        InputStream s = socket.getInputStream();
        byte[] buf = new byte[1];
        int len = 0;
        while ((len = s.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
            Thread.sleep(100000000);
        }

    }
}
