package org.mengyan.netty.common.keepalive;

import org.mengyan.netty.common.OperationResult;

public class KeepaliveOperationResult extends OperationResult {
    private final long time;

    public KeepaliveOperationResult(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
