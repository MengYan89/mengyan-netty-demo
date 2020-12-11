package org.mengyan.netty.common.order;

import org.mengyan.netty.common.Operation;
import org.mengyan.netty.common.OperationResult;

public class OrderOperation extends Operation {

    private int tableId;

    private String dish;


    public OrderOperation(int tableId, String dish) {
        this.tableId = tableId;
        this.dish = dish;
    }

    @Override
    public OperationResult execute() {
        // TODO log

        OrderOperationResult orderResponse = new OrderOperationResult(tableId, dish, true);
        return orderResponse;
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

    @Override
    public String toString() {
        return "OrderOperation{" +
                "tableId=" + tableId +
                ", dish='" + dish + '\'' +
                '}';
    }
}
