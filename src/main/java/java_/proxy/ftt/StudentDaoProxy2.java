package java_.proxy.ftt;

public class StudentDaoProxy2 extends StudentDaoImpl{

    @Override
    public void insertStudent(Object student) {
        System.out.println("在DB1插入了一条学生记录");
        super.insertStudent(new Object());
    }
}
