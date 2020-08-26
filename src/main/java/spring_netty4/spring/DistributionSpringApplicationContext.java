package spring_netty4.spring;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.InetSocketAddress;

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

    private void createWebServer() {
        NioEventLoopGroup bossSelecors = new NioEventLoopGroup(1);
        NioEventLoopGroup workerSelectors = new NioEventLoopGroup(10);

        NioServerSocketChannel serverSocketChannel = new NioServerSocketChannel();
        bossSelecors.register(serverSocketChannel);

        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.pipeline().addLast(new AcceptHandler(workerSelectors));
    }


    protected class AcceptHandler extends ChannelInboundHandlerAdapter {

        private NioEventLoopGroup workerSelector;

        public AcceptHandler(NioEventLoopGroup workerSelector) {
            this.workerSelector = workerSelector;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            ctx.fireChannelRegistered();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            NioSocketChannel client = (NioSocketChannel) msg;
            workerSelector.register(client);
            client.pipeline().addLast(new HttpRequestDecoder());
            client.pipeline().addLast(new HttpHandler());
            client.pipeline().addLast(new HttpResponseEncoder());
            ctx.fireChannelRead(msg);
        }

    }

    @ChannelHandler.Sharable
    protected class HttpHandler extends ChannelInboundHandlerAdapter {

    }

}
