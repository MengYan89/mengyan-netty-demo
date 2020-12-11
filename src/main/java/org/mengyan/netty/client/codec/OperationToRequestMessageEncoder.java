package org.mengyan.netty.client.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.mengyan.netty.common.Operation;
import org.mengyan.netty.common.RequestMessage;
import org.mengyan.netty.common.order.OrderOperation;
import org.mengyan.netty.util.IdUtil;

import java.util.List;

public class OperationToRequestMessageEncoder extends MessageToMessageEncoder<Operation> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Operation operation, List<Object> list) throws Exception {
        RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(), operation);
        list.add(requestMessage);
    }
}
