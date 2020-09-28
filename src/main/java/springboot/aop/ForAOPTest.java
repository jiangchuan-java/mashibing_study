package springboot.aop;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;

/**}
 *
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
//@Aspect
//

/**
 *
 *
 * Aspect : 切面:常规类实现的
 * Join point: 连接点-某个方法的执行: 在spring aop中，连接点始终代表方法的执行
 * Advide : 建议-采取的操作: 切面在特定连接点处采取的操作，不同类型的建议包括"包围","前","后"，包括spring在内的许多aop框架都将建议建模为Interceptor，并在连接点周围维护一系列Interceptor
 * Pointcut : 切入点-表达式: 通过表达式匹配出来的Join point，spring aop核心概念，可以应用在多个对象上
 * Introduction : 其他接口:spring可以用过Introduction使代理的对象实现新的接口
 * Target object : 目标对象:被代理的对象
 * AOP proxy : aop代理:在spring中，aop代理是JDK动态代理或CGLIB代理
 * Weaving : 编织、织入：将切面与其他类链接在一起创建一个建议对象，这一过程可以在编译(Aspect编译器)，或加载，或运营时完成，spring通过运行时完成。
 *
 * 注意事项：
 * 0: spring aop 是基于代理模式实现的，基于代理的性质，是不会拦截目标对象内的调用，目标对象内的object refence是被代理对象本身，走不到代理
 * 1: spring aop 是纯java实现的，不需要特殊的编译过程，不需要控制类加载器的层次结构，因此适用性更广
 * 2: spring aop 当前仅支持方法的连接点(建议在Spring Bean上执行方法),尽管可以在不破坏核心Spring AOP API的情况下添加对字段拦截的支持，但并未实现字段拦截。
 *  如果需要建议字段访问和更新连接点，请考虑使用诸如AspectJ之类的语言。
 * 3:
 *
  */
@Aspect
//@Component, 或者通过typeFilter 将Aspect注释的类可以在component-scan 扫描并加载到beanFacotory中
public class /*Aspect*/ ForAOPTest implements Advice {

    /*advice*/@Around(/*pointcut*/"execution(* package.class.method(param))")
    /*advice*/@Before(/*pointcut*/"execution(* package.class.method(param))")
    /*advice*/@After(/*pointcut*/"execution(* package.class.method(param))")
    /*advice*/@AfterReturning(/*pointcut*/)
    /*advice*/@AfterThrowing(/*pointcut*/)
    public Object around(ProceedingJoinPoint/*join point*/ pjp) throws Throwable {
        System.out.println("123");
        return pjp.proceed();
    }

    //@Pointcut("execution、within、this、target、args、@target、@args、@within、@annotation")


    //代码方式创建aspect 代理对象,这种对象需要自己创建或者自己从beanFactory中获取
    public static Dog buildProxyByCode() {
        Dog dog = new Dog();
        ForAOPTest aspect = new ForAOPTest();
        AspectJProxyFactory factory = new AspectJProxyFactory(dog);
        factory.addAspect(aspect); //aspect中包含了 advice
        Dog proxyDog = factory.getProxy();

        return proxyDog;
    }

    //代码方式创建代理，这种对象需要自己创建或者自己从beanFactory中获取
    public static Dog buildProxyByCode2() {
        Dog dog = new Dog();
        ForAOPTest advice = new ForAOPTest();
        ProxyFactory factory = new ProxyFactory(dog);
        factory.addAdvice(advice);
        factory.addAdvisors();
        Dog proxyDog = (Dog) factory.getProxy();

        return proxyDog;
    }

    //proxyFactoryBean方式创建aspect 代理对象, 优点是可以使用spring管理的advice和join point进行代理的创建
    public static Dog buildProxyByProxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames("bean name in spring context");
        proxyFactoryBean.setTargetName("bean name in spring context");
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.getAdvisors();

        return (Dog) proxyFactoryBean.getObject();
    }

}
