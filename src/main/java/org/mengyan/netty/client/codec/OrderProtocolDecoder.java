package org.mengyan.netty.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.mengyan.netty.common.RequestMessage;
import org.mengyan.netty.common.ResponseMessage;

import java.util.List;

public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        ResponseMessage responseMessage =  new ResponseMessage();

        responseMessage.decode(byteBuf);

        out.add(responseMessage);

    }
}
