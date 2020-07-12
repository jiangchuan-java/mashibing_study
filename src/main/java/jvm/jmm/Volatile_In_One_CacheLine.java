package jvm.jmm;

import sun.misc.Contended;

/**
 * Created by fengtingting on 2020/6/21.
 *
 * (伪共享)，明明volatile操作的是两个不相干的数据，但因为两个数据在同一个cacheLine中，导致两个线程需要互相通知进行一致
 * 测试缓存volatile的底层实现（MESI）与操作系统的cacheLine的关系
 * cacheLine 一般是 64个字节
 */
public class Volatile_In_One_CacheLine {

    //这两个变量的内存地址是连续的,且小于64个字节，所以在一个cacheLine中
    @Contended //jdk1.8加这个注解可以优化，添加-XX:-RestrictContended 生效
    private volatile long p1;

    //这两个变量的内存地址是连续的,且小于64个字节，所以在一个cacheLine中
    @Contended //jdk1.8加这个注解可以优化，添加-XX:-RestrictContended 生效
    private volatile long p2;


    public static void main(String[] args) throws Exception{
        Volatile_In_One_CacheLine object = new Volatile_In_One_CacheLine();
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
                    object.p2 = i;
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
