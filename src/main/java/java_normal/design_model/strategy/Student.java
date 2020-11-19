package java_normal.design_model.strategy;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/15 16:25
 */
public class Student implements Comparable<Student>{

    public int grade;
    public int height;
    public int weight;

    public Student(int grade, int height, int weight) {
        this.grade = grade;
        this.height = height;
        this.weight = weight;
    }

    @Override //实现了分数的比较逻辑，但如果要按其他属性排序，则只能修改代码重新实现
    public int compareTo(Student o) {
        if(this.grade > o.grade) return 1;
        else if (this.grade < o.grade) return -1;
        else return 0;
    }
    @Override
    public String toString() {
        return "Student{" +
                "grade=" + grade +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
