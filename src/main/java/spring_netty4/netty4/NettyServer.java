package spring_netty4.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-8-27
 */
public class NettyServer {

    public void createServer() throws Exception{
        final NioEventLoopGroup bossSelecors = new NioEventLoopGroup(1);
        final NioEventLoopGroup workerSelectors = new NioEventLoopGroup(10);

        NioServerSocketChannel serverSocketChannel = new NioServerSocketChannel();
        bossSelecors.register(serverSocketChannel);
        serverSocketChannel.pipeline().addLast(new AcceptHandler(workerSelectors));
        serverSocketChannel.bind(new InetSocketAddress(8080)).sync();

        serverSocketChannel.closeFuture().addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                bossSelecors.shutdownGracefully();
                workerSelectors.shutdownGracefully();
            }
        });
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
            client.pipeline().addLast(new HttpObjectAggregator(65536));
            client.pipeline().addLast(new HttpHandler());
            client.pipeline().addLast(new HttpResponseEncoder());
        }

    }

    @ChannelHandler.Sharable
    protected class HttpHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
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
}
