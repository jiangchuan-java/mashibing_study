package io_nio.MyJavaNio;

import java.nio.channels.SelectableChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/8/15 22:06
 */
public class SelectorTheadGroup {

    private SelectorThread[] selectors;

    private AtomicInteger index = new AtomicInteger(0);

    private int nThreads;

    public SelectorTheadGroup(int nThreads) {
        this.nThreads = nThreads;
        this.selectors = new SelectorThread[nThreads];
    }

    public void register(SelectableChannel channel) {
        next().register(channel);
    }

    private SelectorThread next() {
        return selectors[index.incrementAndGet() / nThreads];
    }
}
