package algorithm.socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-5-18
 */
public class SocketServer {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket  = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9600));
        serverSocket.setReceiveBufferSize(50);
        while (true) {
            Socket socket = serverSocket.accept();
            byte[] bytes = new byte[10];
            socket.getInputStream().read(bytes);
            System.out.println(new String(bytes));
            byte[] outBytes = new byte[1024*10];
            for(int i=0;i<500;i++) {
                socket.getOutputStream().write(outBytes);
                System.out.println(i);
            }
            System.out.println("写完");
        }
    }
}
