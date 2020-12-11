package org.mengyan.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.mengyan.netty.client.codec.OrderFrameDecoder;
import org.mengyan.netty.client.codec.OrderFrameEncoder;
import org.mengyan.netty.client.codec.OrderProtocolDecoder;
import org.mengyan.netty.client.codec.OrderProtocolEncoder;
import org.mengyan.netty.common.RequestMessage;
import org.mengyan.netty.common.order.OrderOperation;
import org.mengyan.netty.util.IdUtil;

import java.util.concurrent.ExecutionException;

public class Client {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);

        // bootstrap.handler(new LoggingHandler(LogLevel.INFO));

        bootstrap.group(new NioEventLoopGroup());

        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                ChannelPipeline channelPipeline = nioSocketChannel.pipeline();

                channelPipeline.addLast(new OrderFrameDecoder());
                channelPipeline.addLast(new OrderFrameEncoder());
                channelPipeline.addLast(new OrderProtocolDecoder());
                channelPipeline.addLast(new OrderProtocolEncoder());

                channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));

            }
        });

        ChannelFuture channelFuture =  bootstrap.connect("127.0.0.1",8095);

        RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(),new OrderOperation(1001,"fanqie"));
        // 先查看是否连接成功
        channelFuture.sync();

        channelFuture.channel().writeAndFlush(requestMessage);

        channelFuture.channel().closeFuture().get();

    }
}
