package springboot.custom_autoProxy_annotation;

import org.springframework.stereotype.Component;
import springboot.custom_autoProxy_annotation.AutoProxy;
import springboot.custom_autoProxy_annotation.MyMethodInterceptor;

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
