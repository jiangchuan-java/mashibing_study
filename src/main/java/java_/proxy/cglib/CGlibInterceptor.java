package java_.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-5-19
 */
public class CGlibInterceptor implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName()+" proxy");
        Object result = methodProxy.invokeSuper(o,objects);
        return result;
    }
}
