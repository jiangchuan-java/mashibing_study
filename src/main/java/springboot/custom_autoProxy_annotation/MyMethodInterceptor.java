package springboot.custom_autoProxy_annotation;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before intercept exec");
        Object result = proxy.invokeSuper(obj,args);
        System.out.println("after intercept exec");
        return result;
    }
}
