package org.mengyan.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.mengyan.netty.client.codec.*;
import org.mengyan.netty.client.codec.dispatcher.OperationResultFuture;
import org.mengyan.netty.client.codec.dispatcher.RequestPendingCenter;
import org.mengyan.netty.client.codec.dispatcher.ResponseDispatcherHandler;
import org.mengyan.netty.common.Operation;
import org.mengyan.netty.common.OperationResult;
import org.mengyan.netty.common.RequestMessage;
import org.mengyan.netty.common.order.OrderOperation;
import org.mengyan.netty.util.IdUtil;

import java.util.concurrent.ExecutionException;

public class ClientV2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);

        // bootstrap.handler(new LoggingHandler(LogLevel.INFO));

        bootstrap.group(new NioEventLoopGroup());

        RequestPendingCenter requestPendingCenter = new RequestPendingCenter();

        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                ChannelPipeline channelPipeline = nioSocketChannel.pipeline();

                channelPipeline.addLast(new OrderFrameDecoder());
                channelPipeline.addLast(new OrderFrameEncoder());
                channelPipeline.addLast(new OrderProtocolDecoder());
                channelPipeline.addLast(new OrderProtocolEncoder());

                channelPipeline.addLast(new ResponseDispatcherHandler(requestPendingCenter));

                channelPipeline.addLast(new OperationToRequestMessageEncoder());

                channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));

            }
        });

        ChannelFuture channelFuture =  bootstrap.connect("127.0.0.1",8095);

        long streamId = IdUtil.nextId();
        RequestMessage requestMessage = new RequestMessage(streamId,new OrderOperation(1001,"fanqie"));


        OperationResultFuture operationResultFuture = new OperationResultFuture();

        requestPendingCenter.add(streamId, operationResultFuture);


        // 先查看是否连接成功
        channelFuture.sync();

        channelFuture.channel().writeAndFlush(requestMessage);

        OperationResult operationResult = operationResultFuture.get();

        System.out.println(operationResult);

        channelFuture.channel().closeFuture().get();

    }
}
