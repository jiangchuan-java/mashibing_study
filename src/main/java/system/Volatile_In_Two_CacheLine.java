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

    //一共128个字节，在两个array[0]在前64中，array[8]在后64中，所以在两个cacheLine中
    private static volatile long[] array = new long[16];

    static class T1 extends Thread{
        @Override
        public void run(){
            for(int i=0;i<10000_10000l;i++){
                array[0] = i;
            }
        }
    }

    static class T2 extends Thread{
        @Override
        public void run(){
            for(int i=0;i<10000_10000l;i++){
                array[8] = i;
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
