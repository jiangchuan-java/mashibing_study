package ftt;

import ftt.aop.Calculator;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FttTest {
    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Calculator bean = context.getBean(Calculator.class);
        bean.add(1,1);
        System.out.println("--------------------------");
    }
}
