package com.rest.models;

public class payment {
    public enum Method {
        CASH,
        CARD,
        UPI
    }

    private final int id;
    private final int orderId;
    private final int billId;
    private final double amount;
    private final Method method;

    public payment(int id, int orderId, double amount, Method method) {
        this(id, orderId, 0, amount, method);
    }

    public payment(int id, int orderId, int billId, double amount, Method method) {
        if (id <= 0 || orderId <= 0) {
            throw new IllegalArgumentException("Payment and order IDs must be positive.");
        }
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive.");
        }
        if (method == null) {
            throw new IllegalArgumentException("Payment method is required.");
        }
        this.id = id;
        this.orderId = orderId;
        this.billId = billId;
        this.amount = amount;
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getBillId() { return billId; }

    public double getAmount() {
        return amount;
    }

    public Method getMethod() {
        return method;
    }
}
