package java_normal.proxy.ftt;

public class Main {

    public static void main(String[] args) {
        StudentDao studentDao = new StuentDaoProxy(new StudentDaoImpl());
        studentDao.insertStudent(new Object());
    }
}
