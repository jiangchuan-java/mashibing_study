package springboot.dubbo_annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-30
 */
public class DubboImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attrubuteMap = metadata.getAnnotationAttributes(DubboComponentScan.class.getName());
        //1. 获取自定义注解的属性，然后根据属性值做一些处理
        //2. 注册一些自定义的PostPorcessor来处理自定义的一些注解
        //3. 哪里处理的注解的？？？
    }
}
