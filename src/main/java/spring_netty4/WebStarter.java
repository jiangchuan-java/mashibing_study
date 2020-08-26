package spring_netty4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-26
 */
@SpringBootApplication
public class WebStarter {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebStarter.class);
        springApplication.run(args);
    }
}
