package org.mengyan.netty.client.codec.dispatcher;

import org.mengyan.netty.common.OperationResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestPendingCenter {

    private Map<Long, OperationResultFuture> map = new ConcurrentHashMap<>();

    public void add (Long streamId, OperationResultFuture future) {
        this.map.put(streamId, future);
    }

    public void set (Long streamId, OperationResult result) {
        OperationResultFuture future = this.map.get(streamId);
        if (future != null) {
            future.setSuccess(result);
            this.map.remove(streamId);
        }
    }


}
