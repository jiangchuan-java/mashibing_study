package springboot.spring_extension_interface;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import springboot.beida.Student;

@Component("thread")
public class ThreadFactoryBean implements FactoryBean<Thread> {
    private int i = 0;
    @Override
    public Thread getObject() throws Exception {
        Thread thread = new Thread();
        thread.setName("jiangchuan-"+i);
        i++;
        return thread;
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
