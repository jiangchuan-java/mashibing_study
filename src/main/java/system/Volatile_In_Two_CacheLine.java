package system;

/**
 * Created by fengtingting on 2020/6/21.
 *
 * (非伪共享)，volatile操作的是两个不相干的数据，且两个数据在不同的cacheLine中，则两个线程不需要任何通讯来保证一致，自己操作自己的数据即可
 * 测试缓存volatile的底层实现（MESI）与操作系统的cacheLine的关系
 * 测试缓存volatile的底层实现（MESI）与操作系统的cacheLine的关系
 * cacheLine 一般是 64个字节
 */
public class Volatile_In_Two_CacheLine {

    //在class中，这两个变量的内存地址是连续的,且小于64个字节，所以在第一个cacheLine中
    private static volatile long p1;

    //cacheLine padding (缓存行对齐)，用于将两个操作的变量放入不同的cacheLine中
    private static volatile long p2,p3,p4,p5,p6,p7,p8;

    //在class中，这两个变量的内存地址是连续的,中间有多个变量超过64个字节，所以在第二个cacheLine中
    private static volatile long p9;

    static class T1 extends Thread{
        @Override
        public void run(){
            for(int i=0;i<1000_10000l;i++){
                p1 = i;
            }
        }
    }

    static class T2 extends Thread{
        @Override
        public void run(){
            for(int i=0;i<1000_10000l;i++){
                p9 = i;
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
