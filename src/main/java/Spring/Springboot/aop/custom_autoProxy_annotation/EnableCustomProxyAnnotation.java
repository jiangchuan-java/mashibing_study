package Spring.Springboot.aop.custom_autoProxy_annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Des: 允许开启自定义的代理注解
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoProxyAnnotationParser.class)
public @interface EnableCustomProxyAnnotation {
}
