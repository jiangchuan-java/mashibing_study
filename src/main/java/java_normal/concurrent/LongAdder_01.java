package java_normal.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/12 21:36
 */
public class LongAdder_01 {

    @Test
    public void longAdderTest(){
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.hashCode());

    }
}
