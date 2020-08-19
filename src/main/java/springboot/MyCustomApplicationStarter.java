package springboot;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
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
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        beanFactory.registerSingleton("myCustomApplicationStarter", new MyCustomApplicationStarter());
        applicationContext.refresh();
        System.in.read();
    }
}
