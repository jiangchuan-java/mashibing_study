package java_.thread_lock.sync;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by fengtingting on 2020/7/10.
 */
public class MainClass {
    public static void main(String[] args) {
        Class studentClass = Student.class;

        Field[] fields = studentClass.getDeclaredFields();
        /*打印字段*/
        for(Field field : fields){
            System.out.println(field.getName());
        }

        Method[] methods = studentClass.getDeclaredMethods();
        /*打印方法*/
        for(Method method : methods){
            System.out.println(method.getName());
        }

        Student student = new Student();

        System.out.println(ClassLayout.parseInstance(student).toPrintable());

        System.out.println("size : " + GraphLayout.parseInstance(student).totalSize());

    }
}
