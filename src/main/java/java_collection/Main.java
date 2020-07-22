package java_collection;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<Student> set = new TreeSet<>(new StudentAgeComparator());
        set.add(new Student("zhangsan",19));
        set.add(new Student("lisi",32));
        set.add(new Student("wangwu",17));
        set.add(new Student("zhaoliu",20));
        set.add(new Student("jianger",19));

        System.out.println(set);
        System.out.println("-------------------------");

        Set<Student> set2 = new TreeSet<>();
        set2.add(new Student("zhangsan",19));
        set2.add(new Student("lisi",32));
        set2.add(new Student("wangwu",17));
        set2.add(new Student("zhaoliu",20));
        set2.add(new Student("jianger",19));

        System.out.println(set2);
    }


}
