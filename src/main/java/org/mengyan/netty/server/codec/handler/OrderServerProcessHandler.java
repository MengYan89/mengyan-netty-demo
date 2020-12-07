package org.mengyan.netty.server.codec.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.mengyan.netty.common.Operation;
import org.mengyan.netty.common.OperationResult;
import org.mengyan.netty.common.RequestMessage;
import org.mengyan.netty.common.ResponseMessage;

public class OrderServerProcessHandler extends SimpleChannelInboundHandler<RequestMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestMessage requestMessage) throws Exception {
        Operation operation = requestMessage.getMessageBody();
        OperationResult operationResult = operation.execute();

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(requestMessage.getMessageHeader());
        responseMessage.setMessageBody(operationResult);

        channelHandlerContext.writeAndFlush(responseMessage);
    }
}
