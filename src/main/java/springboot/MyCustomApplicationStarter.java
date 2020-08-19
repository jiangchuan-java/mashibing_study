package springboot;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/8/19 21:57
 */
@ComponentScan()
@Component
@Configuration
public class MyCustomApplicationStarter {

    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(MyCustomApplicationStarter.class);
        annotationConfigApplicationContext.refresh();
        System.in.read();
    }
}
