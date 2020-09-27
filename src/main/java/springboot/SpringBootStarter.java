package springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportResource;
import springboot.aop.custom_autoProxy_annotation.EnableCustomProxyAnnotation;
import springboot.aop.custom_autoProxy_annotation.Worker;

/**
 * Created by fengtingting on 2020/6/30.
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@EnableCustomProxyAnnotation
// TODO: 2020/9/5 spring annotation 仅处理符合typeFilter的类 
public class SpringBootStarter {

    private static void buildSpring(String[] args) {
        /*创建这种XmlContext时， beanFactory是在refresh方法里的ObtainBeanFactory中refreshBeanFactory时进行创建的*/
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringBootStarter.class);
        applicationContext.refresh();
        /*创建这种AnotationContext时，在对应的构造方法里创建了beanFactoryr*/
    }

    private static void buldSpringBoot(String[] args){
        //启动springboot
        SpringApplication springApplication = new SpringApplication(SpringBootStarter.class);
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
        Worker worker = applicationContext.getBean(Worker.class);
        worker.doWork();
        worker.stopWork();



    }

    public static void main(String[] args) throws Exception{
        buldSpringBoot(args);
    }
}