package Spring.Springboot.ioc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import Spring.Springboot.custom_typeFilter.CustomExcludeTypeFilter;
import Spring.Springboot.custom_typeFilter.CustomIncludeTypeFilter;

import java.util.concurrent.*;


@ComponentScan(
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = CustomIncludeTypeFilter.class)},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = CustomExcludeTypeFilter.class)})
//这些filter只有在处理该@ComponentScans时才会生效
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
