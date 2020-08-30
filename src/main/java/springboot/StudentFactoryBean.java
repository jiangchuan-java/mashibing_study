package springboot;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import springboot.entry.Student;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/8/30 14:14
 */
@Component
public class StudentFactoryBean implements FactoryBean<Student> {
    @Override
    public Student getObject() throws Exception {

        return new ProxyStudent();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }


    class ProxyStudent extends Student {

    }
}
