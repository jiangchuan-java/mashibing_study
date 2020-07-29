package springboot.spring_extension_interface;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-29
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("自定义BeanDefinitionRegistryPostProcessor, 在AbstractApplicationContext -> refresh -> invokeBeanFactoryPostProcessors 中被调用");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("自定义BeanDefinitionRegistryPostProcessor, 在AbstractApplicationContext -> refresh -> invokeBeanFactoryPostProcessors 中被调用");
    }
}
