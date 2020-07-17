package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by fengtingting on 2020/6/30.
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
    }
}