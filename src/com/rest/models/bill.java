package com.rest.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Itemized bill prepared from an open restaurant order. */
public class bill {
    private final int id;
    private final int orderId;
    private final List<ordereditem> items;
    private final double subtotal;
    private final double tax;
    private final double total;

    public bill(int id, orders order, double taxRate) {
        if (id <= 0) throw new IllegalArgumentException("Bill ID must be positive.");
        if (order == null) throw new IllegalArgumentException("Order is required.");
        if (!Double.isFinite(taxRate) || taxRate < 0) throw new IllegalArgumentException("Tax rate cannot be negative.");
        this.id = id;
        this.orderId = order.getId();
        this.items = Collections.unmodifiableList(new ArrayList<>(order.getItems()));
        this.subtotal = order.getTotal();
        this.tax = subtotal * taxRate;
        this.total = subtotal + tax;
    }

    public int getId() { return id; }
    public int getOrderId() { return orderId; }
    public List<ordereditem> getItems() { return items; }
    public double getSubtotal() { return subtotal; }
    public double getTax() { return tax; }
    public double getTotal() { return total; }
}
