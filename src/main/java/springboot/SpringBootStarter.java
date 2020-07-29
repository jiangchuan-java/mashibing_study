package springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springboot.beida.Student;

/**
 * Created by fengtingting on 2020/6/30.
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class SpringBootStarter {

    private static void buildSpring(String[] args){
        /*创建这种XmlContext时， beanFactory是在refresh方法里的ObtainBeanFactory中refreshBeanFactory时进行创建的*/
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        /*创建这种AnotationContext时，在对应的构造方法里创建了beanFactoryr*/
        context = new AnnotationConfigApplicationContext();
        //获取bean的实例
        Object object = context.getBean("myFirstSpringDemo");
    }

    private static void buldSpringBoot(String[] args){
        //启动springboot
        SpringApplication springApplication = new SpringApplication(SpringBootStarter.class);
        ConfigurableApplicationContext applicationContext = springApplication.run(args);

        Thread thread1 = (Thread) applicationContext.getBean("thread");
        Thread thread2 = (Thread) applicationContext.getBean("thread");
        System.out.println(thread1.getName());
        System.out.println(thread2.getName());

    }

    public static void main(String[] args) {
        buldSpringBoot(args);
    }
}