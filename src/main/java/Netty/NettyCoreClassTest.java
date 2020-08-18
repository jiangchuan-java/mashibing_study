package Netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class NettyCoreClassTest {
    @Test
    public void NioEventLoopGroupTest() throws Exception{
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
        nioEventLoopGroup.execute(() -> {
            try {
                for (;;){
                    System.out.println("hello");
                    TimeUnit.SECONDS.sleep(1);
                }
            }catch (Exception e){

            }
        });
        System.in.read();
    }

    @Test
    public void bootStrapTest() throws Exception{
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(nioEventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter());
                    }
                })
                .bind(new InetSocketAddress("127.0.0.1",9090));
    }
}
