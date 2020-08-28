package spring_netty4.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.eclipse.jetty.server.HttpOutput;

import java.net.InetSocketAddress;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-27
 */
public class NettyServer {

    public void createServer() throws Exception{
        final NioEventLoopGroup bossSelecorsThreads = new NioEventLoopGroup(1);
        final NioEventLoopGroup workerSelectorsThreads = new NioEventLoopGroup(10);

        NioServerSocketChannel serverSocketChannel = new NioServerSocketChannel();
        bossSelecorsThreads.register(serverSocketChannel);
        serverSocketChannel.pipeline().addLast(new AcceptHandler(workerSelectorsThreads));
        serverSocketChannel.bind(new InetSocketAddress(8080)).sync();
    }

    protected class AcceptHandler extends ChannelInboundHandlerAdapter {

        private NioEventLoopGroup workerSelectorsThreads;

        public AcceptHandler(NioEventLoopGroup workerSelectorsThreads) {
            this.workerSelectorsThreads = workerSelectorsThreads;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            ctx.fireChannelRegistered();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(Thread.currentThread().getName()+" accept");
            NioSocketChannel client = (NioSocketChannel) msg;
            client.config().setOption(ChannelOption.SINGLE_EVENTEXECUTOR_PER_GROUP, false);
            client.pipeline().addLast(new HttpRequestDecoder());
            client.pipeline().addLast(new HttpObjectAggregator(65536));
            client.pipeline().addLast(workerSelectorsThreads,new HttpHandler());
            client.pipeline().addLast(workerSelectorsThreads,new HttpOutputHandler());
            client.pipeline().addLast(workerSelectorsThreads,new HttpResponseEncoder());
            workerSelectorsThreads.register(client);
        }

    }

    @ChannelHandler.Sharable
    protected class HttpHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(Thread.currentThread().getName()+" read");
            if(msg instanceof FullHttpRequest){
                FullHttpRequest httpRequest = (FullHttpRequest) msg;
                String uri = httpRequest.uri();
                HttpMethod method = httpRequest.method();
                HttpHeaders headers = httpRequest.headers();
                ByteBuf content = httpRequest.content();
                byte[] bytes = new byte[content.readableBytes()];
                content.readBytes(bytes);
                System.out.println(method.name()+" "+uri+" " + new String(bytes));
                response(ctx);
            }
        }

        private void response(ChannelHandlerContext ctx) {
            // 1.设置响应
            FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer("123", CharsetUtil.UTF_8));

            resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
            resp.headers().set(HttpHeaderNames.CONTENT_LENGTH, resp.content().readableBytes());

            // 2.发送
            // 注意必须在使用完之后，close channel
            //ctx.writeAndFlush(resp); //这种方式是找next()，然后进行调用
            ctx.channel().writeAndFlush(resp); //这种方式是从tail开始，从后往前进行调用
        }

    }

    @ChannelHandler.Sharable
    protected class HttpOutputHandler extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println(Thread.currentThread().getName()+" write");
            ctx.write(msg, promise);
        }

    }
}
