package java_.proxy.ftt;

public class StuentDaoProxy implements StudentDao{

    StudentDao studentDao;

    public StuentDaoProxy(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @Override
    public void insertStudent(Object student) {
        System.out.println("插入DB2一条学生记录");
        studentDao.insertStudent(new Object());
    }
}
