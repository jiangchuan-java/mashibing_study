package java_.proxy;

import java_.proxy.cglib.CGlibInterceptor;
import java_.proxy.jdk.JdkInvocationHandler;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-5-19
 */
public class Test {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        HelloWorld helloWorld = new HelloWorld();

        /* CGLIG */
        CGlibInterceptor interceptor = new CGlibInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(helloWorld.getClass());
        enhancer.setCallback(interceptor);

        HelloWorld cglibProxy = (HelloWorld) enhancer.create();

        enhancer.setSuperclass(cglibProxy.getClass());
        enhancer.setCallback(interceptor);

       enhancer.create();

        System.out.println("cglib2 - 动态代理对象的类型："+cglibProxy.getClass().getName());
        cglibProxy.say();
        cglibProxy.eat();
        /* CGLIG */

        /* jdk */
        Say sayWorld = new HelloWorld();
        JdkInvocationHandler invocationHandler = new JdkInvocationHandler(sayWorld);
        ClassLoader classLoader = sayWorld.getClass().getClassLoader();
        Class[] interfaces = sayWorld.getClass().getInterfaces();

        Say jdkProxy = (Say) Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
        Class c = jdkProxy.getClass();
        Class[] interfs = c.getInterfaces();
        System.out.println("jdk - 动态代理对象的类型："+jdkProxy.getClass().getName());
        jdkProxy.say();
        jdkProxy.eat();
        /* jdk */
    }
}
