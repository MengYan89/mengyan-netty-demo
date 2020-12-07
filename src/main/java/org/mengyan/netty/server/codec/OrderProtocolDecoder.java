package org.mengyan.netty.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.mengyan.netty.common.RequestMessage;

import java.util.List;

public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        RequestMessage requestMessage =  new RequestMessage();

        requestMessage.decode(byteBuf);

        out.add(requestMessage);

    }
}
