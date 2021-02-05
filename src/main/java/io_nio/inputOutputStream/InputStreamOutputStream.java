package io_nio.inputOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Author: jiangchuan
 * <p>
 * @Date: 21-2-5
 */
public class InputStreamOutputStream {

    public void readBytesFromInputStream() throws Exception{

        Socket socket = new Socket("127.0.0.1",6370);

        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte[] tempBytes = new byte[1024*4];
        int n = 0;
        while (-1 != (n = inputStream.read(tempBytes))) { //读4K
            output.write(tempBytes, 0, n); //写4K,，读取到自己的byte数组中并记录总数，容量不足进行数组扩容
        }

        byte[] bytes = output.toByteArray(); //创建个新的数组，长度等于总数，从自己的byte数组中复制到新的数组中，并返回新数组
    }
}
