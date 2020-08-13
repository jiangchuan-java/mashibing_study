package ftt.util;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Component
public class LogUtil {
    @Pointcut("execution(* ftt.aop.Calculator.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(){
        System.out.println("开始执行...");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("结束执行...");
    }

    @AfterThrowing("pointCut()")
    public void exce(){
        System.out.println("执行异常...");
    }

    @AfterReturning("pointCut()")
    public void over(){
        System.out.println("代码块执行完成...");
    }
}
