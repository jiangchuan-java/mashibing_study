package servlet_container.tomcat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import java.net.InetSocketAddress;


/**
 * 实现一个tomcat服务，可以进行网络通信,使用java-nio
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/8/13 23:23
 */
public class TomcatServer {

    public void start() throws Exception{
        init();
    }

    public void init() throws Exception{
        NioEventLoopGroup selectorPool = new NioEventLoopGroup(1);
        NioServerSocketChannel server = new NioServerSocketChannel();
        selectorPool.register(server);
        server.pipeline().addLast(new MyAcceptHandler(selectorPool, new MyReadHandler(selectorPool)));

        ChannelFuture channelFuture = server.bind(new InetSocketAddress("127.0.0.1",9090));
        channelFuture.sync();
        System.out.println("server start at 9090");
        channelFuture.channel().closeFuture().sync();

    }

    //接收请求的handler
    private class MyAcceptHandler extends ChannelInboundHandlerAdapter {

        private NioEventLoopGroup selectorPool;
        private MyReadHandler readHandler;

        public MyAcceptHandler(NioEventLoopGroup selectorPool, MyReadHandler readHandler) {
            this.selectorPool = selectorPool;
            this.readHandler = readHandler;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("do registered and fire active (if first register)");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            NioSocketChannel client = (NioSocketChannel) msg;
            selectorPool.register(client);
            client.pipeline().addLast(readHandler);

        }

    }

    //处理读写的handler
    @ChannelHandler.Sharable
    private class MyReadHandler extends ChannelInboundHandlerAdapter {

        private NioEventLoopGroup selectorPool;

        public MyReadHandler(NioEventLoopGroup selectorPool) {
            this.selectorPool = selectorPool;
        }

        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;
            CharSequence str = byteBuf.getCharSequence(0,byteBuf.readableBytes(), CharsetUtil.UTF_8);
            System.out.println("receive from client " + ctx.channel().remoteAddress().toString()+" with " + str);
            ctx.writeAndFlush(byteBuf);
        }
    }

    //main方法启动tomcat，访问8080端口可以出猫的页面
    public static void main(String[] args) throws Exception{
        TomcatServer tomcat = new TomcatServer();
        tomcat.start();
    }



}
