package org.mengyan.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.mengyan.netty.server.codec.OrderFrameDecoder;
import org.mengyan.netty.server.codec.OrderFrameEncoder;
import org.mengyan.netty.server.codec.OrderProtocolDecoder;
import org.mengyan.netty.server.codec.OrderProtocolEncoder;
import org.mengyan.netty.server.codec.handler.OrderServerProcessHandler;
import sun.rmi.runtime.Log;

import java.util.concurrent.ExecutionException;

public class Server {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);

        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));

        NioEventLoopGroup boss = new NioEventLoopGroup(0, new DefaultThreadFactory("boss"));

        NioEventLoopGroup worker = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));

        serverBootstrap.group(boss, worker);

        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                ChannelPipeline channelPipeline = nioSocketChannel.pipeline();

                channelPipeline.addLast("frameDecoder",new OrderFrameDecoder());
                channelPipeline.addLast("frameEncoder" ,new OrderFrameEncoder());
                channelPipeline.addLast("protocolDecoder", new OrderProtocolDecoder());
                channelPipeline.addLast("protocolEncoder",new OrderProtocolEncoder());
                channelPipeline.addLast("serverProcessHandler", new OrderServerProcessHandler());

                channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));

            }
        });

        ChannelFuture channelFuture =  serverBootstrap.bind(8095).sync();

        channelFuture.channel().closeFuture().get();

    }
}
