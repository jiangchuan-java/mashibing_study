package system;

/**
 * Created by fengtingting on 2020/6/21.
 * 测试缓存volatile的底层实现（MESIS）与操作系统的cacheLine的关系
 * cacheLine 一般是 64个字节
 * 测试1：两个cpu读取同一个cacheLine，但修改的是里面的不同数据，则MESI介入，两个cpu之间要进行通信，进行缓存一致性
 * 测试2：两个cpu读取两个cacheLine，分别修改自己cacheLine中的数据，理论上MESI不会介入，两个cpu各自修改
 */
public class Volatile_In_One_CacheLine {

    private static volatile long[] array = new long[2];//一共16个字节，在同一个cacheLine中

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
                array[1] = i;
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
