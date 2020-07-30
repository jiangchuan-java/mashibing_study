package springboot.dubbo_annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-7-30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DubboImportBeanDefinitionRegistrar.class)
public @interface DubboComponentScan {


}
