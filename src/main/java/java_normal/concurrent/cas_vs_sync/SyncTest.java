package java_normal.concurrent.cas_vs_sync;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-11-16
 */
public class SyncTest {

    public static void main(String args[]) throws Exception{

        for(int i=0;i<1;i++){
            new Thread(new Runnable() {
                int k = 0;
                @Override
                public void run() {
                    while (true) {
                        //多线程竞争下：
                        //futuex(共享变量地址，操作，值，值)
                        //futex(0x7fd26013ea54, FUTEX_WAKE_OP_PRIVATE, 1, 1, 0x7fd26013ea50, {FUTEX_OP_SET, 0, FUTEX_OP_CMP_GT, 1}) = 1
                        //write(1, "92", 2)
                        synchronized (String.class) {
                            k++;
                            System.out.println(k);
                        }
                    }
                }
            }).start();
        }
    }
}
