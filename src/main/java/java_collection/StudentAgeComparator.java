package java_collection;

import java.util.Comparator;

public class StudentAgeComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getAge() == o2.getAge()){
            return 0;
        }else if(o1.getAge() > o2.getAge()){
            return 1;
        }else {
            return -1;
        }
    }
}
