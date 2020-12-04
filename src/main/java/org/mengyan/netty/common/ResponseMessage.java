package org.mengyan.netty.common;

public class ResponseMessage extends Message<OperationResult> {
    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return OperationType.fromOpcode(opCode).getOperationResultClazz();
    }
}
