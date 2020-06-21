package system;

import sun.misc.Contended;

/**
 * Created by fengtingting on 2020/6/21.
 *
 * (伪共享)，明明volatile操作的是两个不相干的数据，但因为两个数据在同一个cacheLine中，导致两个线程需要互相通知进行一致
 * 测试缓存volatile的底层实现（MESI）与操作系统的cacheLine的关系
 * cacheLine 一般是 64个字节
 */
public class Volatile_In_One_CacheLine {

    //在class中，这两个变量的内存地址是连续的,且小于64个字节，所以在一个cacheLine中
    /* @Contended 据说jdk1.8加这个注解可以优化，但实际也没生效*/
    private static volatile long p1;

    //在class中，这两个变量的内存地址是连续的,且小于64个字节，所以在一个cacheLine中
    /* @Contended 据说jdk1.8加这个注解可以优化，但实际也没生效*/
    private static volatile long p2;

    static class T1 extends Thread{
        @Override
        public void run(){
            for(int i=0;i<1000_10000l;i++){
                p1 = i;
                //p2
            }
        }
    }

    static class T2 extends Thread{
        @Override
        public void run(){
            for(int i=0;i<1000_10000l;i++){
            p2 = i;
            //p1
        }
    }
    }

    public static void main(String[] args) throws Exception{
        long begin = System.currentTimeMillis();
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
    }
}
