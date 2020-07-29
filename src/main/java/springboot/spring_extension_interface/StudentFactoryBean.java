package springboot.spring_extension_interface;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import springboot.beida.Student;

import java.util.Random;

//@Component("student")
public class StudentFactoryBean implements FactoryBean<Student> {
    @Override
    public Student getObject() throws Exception {
        System.out.println("调用自定义的StudentFactoryBean");
        Student student = new Student();
        return student;
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
