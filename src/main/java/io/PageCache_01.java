package io;

import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-2
 */
public class PageCache_01 {

    public static void main(String[] args) throws Exception{

        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);

        String pid = name.split("@")[0];
        System.out.println("Pid is:"+ pid);

        File file = new File("/home/jiangchuan/workspace/mashibing_study/src/main/java/io/b.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2048);
        byteBuffer.put("hello".getBytes());

        System.in.read();

        RandomAccessFile randomAccessFile = new RandomAccessFile("/home/jiangchuan/workspace/mashibing_study/src/main/java/io/a.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0,5120);

        System.in.read();


        mappedByteBuffer.put("456789".getBytes());

        System.in.read();



    }
}
