package Spring.Springboot.ioc.custom_factoryBean;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("student")
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
