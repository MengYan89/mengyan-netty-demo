package org.mengyan.netty.client.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 *
 * 注意：LengthFieldBasedFrameDecoder如果不需要解析出Length头只需要内容的话，请设置initialBytesToStrip变量
 *      设置的长度应与lengthFieldLength相等
 */
public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder {
    public OrderFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2, 0, 2);
    }

}
