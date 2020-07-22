package java_.proxy.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-5-20
 */
@Aspect
public class HelloWorldAspect {

   @Before("execution(* proxy.HelloWorld.say())")
    public void before(JoinPoint joinPoint){
       System.out.println("aspectj before");
   }

    @After("execution(* proxy.HelloWorld.eat())")
    public void after(JoinPoint joinPoint){
        System.out.println("aspectj after");
    }
}
