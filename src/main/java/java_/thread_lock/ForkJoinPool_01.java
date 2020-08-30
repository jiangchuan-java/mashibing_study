package java_.thread_lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/8/30 12:56
 */
public class ForkJoinPool_01 {

    public static void main(String[] args) {
        ExecutorService forkJoinPool = Executors.newWorkStealingPool();
    }
}
