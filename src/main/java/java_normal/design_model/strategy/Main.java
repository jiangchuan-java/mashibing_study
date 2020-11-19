package java_normal.design_model.strategy;

import java.util.Arrays;
import java.util.Collections;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/15 16:30
 */
public class Main {
    public static void main(String[] args) {
        Student[] students = {new Student(4,2,3),
                new Student(2,3,1),
                new Student(1,4,2),
                new Student(3,1,4)};
        //students内置，但只能按分数排序，其他排序需要修改代码实现
        Collections.sort(Arrays.asList(students));
        //身高的比较器,比较器随意使用，且互相独立，无需修改代码，只需扩展即可
        Collections.sort(Arrays.asList(students), new HeightComparator());
        //体重的比较器
        Collections.sort(Arrays.asList(students), new WeightComparator());
        //分数的比较器
        Collections.sort(Arrays.asList(students), new GradeComparator());

        for (Student student : students) {
            System.out.println(student);
        }
    }
}
