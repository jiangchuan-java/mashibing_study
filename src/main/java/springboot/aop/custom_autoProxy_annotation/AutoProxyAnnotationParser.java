package springboot.aop.custom_autoProxy_annotation;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;
import java.util.Objects;

/**
 * @Des: 解析自定义的AutoProxy标签，并生成动态代理类
 * 1: 这个类本身被import注解导入，所以在ConfigurationClassPostProcessor中被加载进beanFactory中
 * 2: 这个类是个BeanPostProcessor，所以在registerBeanPostProcessor方法中被实例化
 * 3: 这个类是BeanPostProcessor，所以在bean实例化的时候被调用，参考AOP的实现机制
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
public class AutoProxyAnnotationParser implements InstantiationAwareBeanPostProcessor  {

    private MethodInterceptor methodInterceptor;

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        AutoProxy autoProxy = beanClass.getAnnotation(AutoProxy.class);
        if(Objects.nonNull(autoProxy)){
            Class<MethodInterceptor> proxyClass = (Class<MethodInterceptor>) autoProxy.proxyClass();
            if(Objects.isNull(methodInterceptor)){
                return createProxy(beanClass, proxyClass);
            }
        }
        return null;
    }

    private Object createProxy(Class<?> beanClass, Class<MethodInterceptor> proxyClass){
        try {
            if(Objects.isNull(methodInterceptor)){
                methodInterceptor = proxyClass.newInstance();
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(beanClass);
                enhancer.setCallback(methodInterceptor);
                return enhancer.create();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
