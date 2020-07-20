package springboot;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

/**
 * Created by fengtingting on 2020/6/30.
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class Application {

    public static void main(String[] args) {
        //启动springboot
        SpringApplication springApplication = new SpringApplication(Application.class);
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition("student");
        System.out.println(1);

       /* //创建Spring上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取bean的实例
        Object object = context.getBean("myFirstSpringDemo");
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
*/
    }
}