package jvm.jmm;

/**
 * Created by fengtingting on 2020/6/21.
 *
 * (非伪共享)，volatile操作的是两个不相干的数据，且两个数据在不同的cacheLine中，则两个线程不需要任何通讯来保证一致，自己操作自己的数据即可
 * 测试缓存volatile的底层实现（MESI）与操作系统的cacheLine的关系
 * 测试缓存volatile的底层实现（MESI）与操作系统的cacheLine的关系
 * cacheLine 一般是 64个字节
 */
public class Volatile_In_Two_CacheLine {

    //(缓存行对齐)，用于将两个操作的变量放入不同的cacheLine中
    private long p2,p3,p4,p5,p6,p7,p8; //cacheLine padding

    //这两个变量的内存地址是连续的,且小于64个字节，所以在第一个cacheLine中
    private volatile long p1;

    private long p10,p11,p12,p13,p14,p15,p16;  //cacheLine padding

    //这两个变量的内存地址是连续的,中间有多个变量超过64个字节，所以在第二个cacheLine中
    private static volatile long p9;


    public static void main(String[] args) throws Exception{
        Volatile_In_Two_CacheLine object = new Volatile_In_Two_CacheLine();
        long begin = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000_10000l;i++){
                    object.p1 = i;
                    //p2
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000_10000l;i++){
                    object.p9 = i;
                    //p2
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
    }
}
