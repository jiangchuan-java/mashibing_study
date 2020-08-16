package io_nio.MyJavaNio;

import java.nio.channels.SelectableChannel;
import java.nio.channels.ServerSocketChannel;
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

    private SelectorTheadGroup childGroup;

    private AtomicInteger index = new AtomicInteger(0);

    private int nThreads;

    public SelectorTheadGroup(int nThreads) {
        this.nThreads = nThreads;
        this.selectors = new SelectorThread[nThreads];
    }

    public void register(SelectableChannel channel) {
        SelectorThread selectorThread = next();
        if(channel instanceof ServerSocketChannel){
            selectorThread.setChildGroup(childGroup);
        }
        selectorThread.register(channel);
    }

    private SelectorThread next() {
        SelectorThread selectorThread =  selectors[index.incrementAndGet() % nThreads];
        if(selectorThread == null){
            selectorThread = new SelectorThread();
        }
        return selectorThread;
    }

    public SelectorTheadGroup getChildGroup() {
        return childGroup;
    }

    public void setChildGroup(SelectorTheadGroup childGroup) {
        this.childGroup = childGroup;
    }
}
