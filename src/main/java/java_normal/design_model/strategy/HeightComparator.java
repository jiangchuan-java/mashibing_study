package java_normal.design_model.strategy;

import java.util.Comparator;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/15 16:28
 */
public class HeightComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if(o1.height > o2.height) return 1;
        else if(o1.height < o2.height) return -1;
        else return 0;
    }
}
