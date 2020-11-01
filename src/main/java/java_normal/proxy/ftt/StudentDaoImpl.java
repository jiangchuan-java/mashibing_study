package java_normal.proxy.ftt;

public class StudentDaoImpl implements StudentDao{
    @Override
    public void insertStudent(Object student) {
        System.out.println("在DB1插入了一条学生记录");
    }
}
