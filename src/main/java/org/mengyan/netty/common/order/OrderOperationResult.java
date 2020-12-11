package org.mengyan.netty.common.order;

import org.mengyan.netty.common.OperationResult;

public class OrderOperationResult extends OperationResult {
    private int tableId;

    private String dish;

    private boolean complete;

    public OrderOperationResult(int tableId, String dish, boolean complete) {
        this.tableId = tableId;
        this.dish = dish;
        this.complete = complete;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "OrderOperationResult{" +
                "tableId=" + tableId +
                ", dish='" + dish + '\'' +
                ", complete=" + complete +
                '}';
    }
}
