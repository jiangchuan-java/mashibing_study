package io_nio.mmap_sendfile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 通过Java-Api验证一下mmap与sendFiled的使用方式
 */
public class Mmap_SendFile {

    //mmap内存映射，用户空间写数据直接写入到pageCache中，减少了系统调用 以及省略了数据从用户空间拷贝到内核空间
    public static void mmap_01() throws Exception{
        RandomAccessFile randomAccessFile =
                new RandomAccessFile("D:\\jiangchuan\\mashibing_study\\src\\main\\java\\io_nio\\mmap_sendfile\\config.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        //下面是mmap的具体demo
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,16);
        String txt = "name=jiangchuan";
        mappedByteBuffer.put(txt.getBytes());
        System.out.println("向mmap 写入数据: "+txt);

        mappedByteBuffer.flip(); //重置游标，因为要读取了
        byte[] bytes = new byte[16];
        mappedByteBuffer.get(bytes,0,15);
        System.out.println("从mmap 读取到数据: "+new String(bytes));
    }

    //sendFile 零拷贝，在内核空间直接将数据从文件输出到了socket中
    public static void sendFile_01() throws Exception{
        RandomAccessFile randomAccessFile =
                new RandomAccessFile("D:\\jiangchuan\\mashibing_study\\src\\main\\java\\io_nio\\mmap_sendfile\\config.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        SocketChannel socketChannel = SocketChannel.open();

        //下面是sendFile的具体demo,只能是将某个文件写入到网卡中，反向不行(至少java没实现)
        fileChannel.transferTo(0,16, socketChannel);
    }

    public static void main(String[] args) throws Exception{
        Mmap_SendFile.mmap_01();
    }
}
