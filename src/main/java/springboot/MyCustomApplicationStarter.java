package springboot;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import springboot.typeFilter.CustomIncludeTypeFilter;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/8/19 21:57
 */
@ComponentScan(
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = CustomIncludeTypeFilter.class)},
        excludeFilters = {}
)
@Component
/**
 * application.xml
 * <beans>
 *     <bean><bean/>
 *     <bean><bean/>
 *     <bean><bean/>
 * <beans/>
 */
public class MyCustomApplicationStarter {

    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyCustomApplicationStarter.class);
        context.refresh();
        System.out.println(context.getBean(Ftt.class));
        System.in.read();
    }
}
