package sington;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fengtingting on 2020/6/19.
 */
public class Atomic {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("jiang");
        list.add("chuan");
        list.add("ftt");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            list.remove(str);
        }
        System.out.println(list.size());
    }
}
