package org.mengyan.netty.common.auth;

import org.mengyan.netty.common.OperationResult;

public class AuthOperationResult extends OperationResult {
    private final boolean passAuth;

    public AuthOperationResult(boolean passAuth) {
        this.passAuth = passAuth;
    }

    public boolean isPassAuth() {
        return passAuth;
    }

    @Override
    public String toString() {
        return "AuthOperationResult{" +
                "passAuth=" + passAuth +
                '}';
    }
}
