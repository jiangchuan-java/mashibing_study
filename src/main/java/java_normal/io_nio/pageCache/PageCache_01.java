package java_normal.io_nio.pageCache;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-2
 */
public class PageCache_01 {

    static byte[] data = "123456789\n".getBytes();


    public static void main(String[] args) throws Exception{

/*        //堆内存数组分配,无任何系统调用，虚拟内存分配后，对应物理内存的使用也在增长，满足映射关系
        for(int i=0;i<10000;i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            byteBuffer.put("123".getBytes());
            System.out.println("build onHeap array");
        }
        //对外内存数组分配,有系统调用,设置某些虚拟内存可读写,虚拟内存分配后，对应物理内存的使用也在增长，满足映射关系
        for(int i=0;i<10000;i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
            byteBuffer.put("123".getBytes());
            *//*
            * mprotect(0x7f5dc8140000, 4096, PROT_READ|PROT_WRITE) = 0 系统调用
            * *//*
            System.out.println("build offHeap array");
        }*/
        //内存分配后需要写入磁盘,虚拟内存分配后，对应物理内存的使用也在增长，满足映射关系
        File file = new File("/home/jiangchuan/io/xxoo.txt");
        FileOutputStream outputStream = new FileOutputStream(file);
        while(true){
            Thread.sleep(100);
            outputStream.write(data);
            outputStream.getFD().sync();
        }


    }
}
