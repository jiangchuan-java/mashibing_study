package servlet_container.tomcat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/8/16 10:48
 */
public class NettyClient {

    public Channel connect() throws Exception {
        Channel channel =  init();
        return channel;
    }

    public Channel init() throws Exception{
        NioEventLoopGroup selector = new NioEventLoopGroup(1);
        NioSocketChannel client = new NioSocketChannel();;
        client.pipeline().addLast(new MyReadHandler());
        selector.register(client);

        ChannelFuture channelFuture = client.connect(new InetSocketAddress("127.0.0.1",9090));
        channelFuture.sync();
        System.out.println("client connect server-> 127.0.0.1:9090");
        return channelFuture.channel();
    }

    private class MyReadHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;
            CharSequence str = byteBuf.getCharSequence(0,byteBuf.readableBytes(), CharsetUtil.UTF_8);
            System.out.println(Thread.currentThread().getName()+" receive from server " + ctx.channel().remoteAddress().toString()+" with " + str);
        }
    }

    public static void main(String[] args) throws Exception{
        NettyClient client = new NettyClient();
        Channel channel = client.connect();
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello server".getBytes());
        channel.writeAndFlush(byteBuf);
        System.in.read();
    }


}
