package org.mengyan.netty.common.keepalive;

import org.mengyan.netty.common.Operation;
import org.mengyan.netty.common.OperationResult;

public class KeepaliveOperation extends Operation {
    private long time;

    public KeepaliveOperation() {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        KeepaliveOperationResult orderResponse = new KeepaliveOperationResult(time);
        return orderResponse;
    }
}
