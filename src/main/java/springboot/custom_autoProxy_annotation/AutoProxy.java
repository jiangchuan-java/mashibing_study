package springboot.custom_autoProxy_annotation;

import java.lang.annotation.*;

/**
 * @Des: 用于给某个类自动进行代理，用指定的代理类
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface AutoProxy {

    /**
     * 指定代理类
     * @return
     */
    Class<?> proxyClass();
}
