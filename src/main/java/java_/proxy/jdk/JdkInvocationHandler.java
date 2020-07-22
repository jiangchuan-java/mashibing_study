package java_.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-5-19
 */
public class JdkInvocationHandler implements InvocationHandler {

    private Object object;

    public JdkInvocationHandler(Object object){
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName()+ " proxy");
        Object result = method.invoke(object, args);
        return result;
    }
}
