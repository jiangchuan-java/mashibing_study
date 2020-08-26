package spring_netty4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring_netty4.spring.DistributionSpringApplicationContext;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-26
 */
@SpringBootApplication
public class WebStarter {

    public static void main(String[] args) {
        DistributionSpringApplicationContext applicationContext = new DistributionSpringApplicationContext();
        applicationContext.register(WebStarter.class);
        applicationContext.refresh();
    }
}
