package springboot.spring_extension_interface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
/**
 * @Configuration注解与<Beans></Beans>标签效果等同。
 * 标识这个类中含有一个或多个@Bean声明的实体
 */
public class ThreadPoolExecutorConfiguration {

    @Bean
    public ThreadPoolExecutor getSingleThreadPoolExecutor(){
        ThreadPoolExecutor singlePoolExecutor =
                new ThreadPoolExecutor(1,1,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        return singlePoolExecutor;
    }

    @Bean
    public ThreadPoolExecutor getCacheThreadPoolExecutor(){
        ThreadPoolExecutor cachePoolExecutor =
                new ThreadPoolExecutor(1,Integer.MAX_VALUE,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        return cachePoolExecutor;
    }

    @Bean
    public ThreadPoolExecutor getFixedThreadPoolExecutor(){
        ThreadPoolExecutor fixedPoolExecutor =
                new ThreadPoolExecutor(200,200,60, TimeUnit.SECONDS,new SynchronousQueue<>());
        return fixedPoolExecutor;
    }
}
