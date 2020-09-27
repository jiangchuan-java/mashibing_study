package springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**}
 *
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
//@Aspect
//@Component

/**
 *
 * Aspect : 切面:常规类实现的
 * Join point: 连接点-某个方法的执行: 在spring aop中，连接点始终代表方法的执行
 * Advide : 建议-特殊的操作: 切面在特定连接点处采取的操作，不同类型的建议包括"包围","前","后"，包括spring在内的许多aop框架都将建议建模为Interceptor，并在连接点周围维护一系列Interceptor
 * Pointcut : 切入点-表达式: 通过表达式匹配出来的Join point，spring aop核心概念，可以应用在多个对象上
 * Introduction : 其他接口:spring可以用过Introduction使代理的对象实现新的接口
 * Target object : 目标对象:在spring aop中始终是代理对象, 这里应该是被代理的对象
 * AOP proxy : aop代理:在spring中，aop代理是JDK动态代理或CGLIB代理
 * Weaving : 编织、织入：将切面与其他类链接在一起创建一个建议对象，这一过程可以在编译(Aspect编译器)，或加载，或运营时完成，spring通过运行时完成。
 *
 *
  */
public class /*Aspect*/ ForAOPTest {

    @Around/*advice*/("execution(* package.class.method(param))"/*pointcut, */)
    @Before("")
    @After("")
    @AfterReturning
    @AfterThrowing
    public Object around(ProceedingJoinPoint/*join point*/ pjp) throws Throwable {
        System.out.println("123");
        return pjp.proceed();
    }
}
