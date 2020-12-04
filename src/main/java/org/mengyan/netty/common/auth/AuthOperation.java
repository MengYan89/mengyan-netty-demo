package org.mengyan.netty.common.auth;

import org.mengyan.netty.common.Operation;
import org.mengyan.netty.common.OperationResult;

public class AuthOperation extends Operation {
    private final String userName;
    private final String password;

    public AuthOperation(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public OperationResult execute() {
        if ("admin".equalsIgnoreCase(this.userName)) {
            AuthOperationResult orderResult = new AuthOperationResult(true);
            return orderResult;
        }
        return new AuthOperationResult(false);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
