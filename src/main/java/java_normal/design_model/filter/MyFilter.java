package java_normal.design_model.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/11/18 22:39
 */
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);
    }
}
