package springboot.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-3
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
public class Aop {
}
