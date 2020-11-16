package java_normal.collection;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/1 19:49
 */
public class QueueAndStack {

    public static void main(String[] args) {
        Queue<Integer> arrayQueue = new ArrayBlockingQueue<Integer>(10);
        Queue<Integer> linkedQueue = new LinkedBlockingQueue<>();

        Stack<Integer> stack = new Stack<>();
    }
}
