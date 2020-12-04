package org.mengyan.netty.common;

import org.mengyan.netty.common.auth.AuthOperation;
import org.mengyan.netty.common.auth.AuthOperationResult;
import org.mengyan.netty.common.keepalive.KeepaliveOperation;
import org.mengyan.netty.common.keepalive.KeepaliveOperationResult;
import org.mengyan.netty.common.order.OrderOperation;
import org.mengyan.netty.common.order.OrderOperationResult;

public enum OperationType {
    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    KEEPALIVE(2, KeepaliveOperation.class, KeepaliveOperationResult.class),
    ORDER(3, OrderOperation.class, OrderOperationResult.class);

    private int opCode;
    private Class<? extends  Operation> operationClazz;
    private Class<? extends OperationResult> operationResultClazz;

    OperationType(int opCode, Class<? extends Operation> operationClazz, Class<? extends OperationResult> operationResultClazz) {
        this.opCode = opCode;
        this.operationClazz = operationClazz;
        this.operationResultClazz = operationResultClazz;
    }

    public int getOpCode() {
        return opCode;
    }

    public Class<? extends Operation> getOperationClazz() {
        return operationClazz;
    }

    public Class<? extends OperationResult> getOperationResultClazz() {
        return operationResultClazz;
    }

    public static OperationType fromOpcode(int opCode) {
        for (OperationType operationType : OperationType.values()) {
            if (operationType.getOpCode() == opCode) {
                return operationType;
            }
        }
        return null;
    }

    public static OperationType fromOperation(Operation operation) {
        Class<? extends Operation> operationClazz = operation.getClass();
        for (OperationType operationType : OperationType.values()) {
            if (operationClazz.equals(operationType.getOperationClazz()) ) {
                return operationType;
            }
        }
        return null;
    }

}
