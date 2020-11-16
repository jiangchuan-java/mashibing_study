package Spring.Springboot.aop.custom_autoProxy_annotation;

import org.springframework.stereotype.Component;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-6
 */
@Component(value = "worker")
@AutoProxy(proxyClass = MyMethodInterceptor.class)
public class Worker {

    public void doWork(){
        System.out.println("I'm doing ...");
    }

    public void stopWork(){
        System.out.println("stop work ...");
    }
}
