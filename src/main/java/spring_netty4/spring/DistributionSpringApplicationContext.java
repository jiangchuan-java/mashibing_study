package spring_netty4.spring;


import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_netty4.netty4.NettyServer;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-26
 */
public class DistributionSpringApplicationContext extends AnnotationConfigApplicationContext {

    @Override
    protected void onRefresh() {
        super.onRefresh();
        try {
            createWebServer();
        }
        catch (Throwable ex) {
            throw new ApplicationContextException("Unable to start reactive web server", ex);
        }
    }

    private void createWebServer() throws Exception{
        NettyServer nettyServer = new NettyServer();
        nettyServer.createServer();
    }


}
