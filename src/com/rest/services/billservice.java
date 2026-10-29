package com.rest.services;

import com.rest.models.bill;
import com.rest.models.orders;
import com.rest.respository.billrepository;

public class billservice {
    private final billrepository repository;
    private final double taxRate;
    private int nextBillId = 1;

    public billservice(billrepository repository) { this(repository, 0.0); }

    /** @param taxRate tax as a fraction, e.g. 0.05 for 5%; defaults to 0% */
    public billservice(billrepository repository, double taxRate) {
        if (repository == null) throw new IllegalArgumentException("Bill repository is required.");
        if (!Double.isFinite(taxRate) || taxRate < 0) throw new IllegalArgumentException("Tax rate cannot be negative.");
        this.repository = repository;
        this.taxRate = taxRate;
    }

    public bill generateBill(orders order) {
        if (order == null) throw new IllegalArgumentException("Order was not found.");
        if (order.getStatus() != orders.Status.OPEN) throw new IllegalStateException("A bill can only be generated for an open order.");
        if (order.getItems().isEmpty()) throw new IllegalStateException("Cannot bill an empty order.");
        bill existing = repository.findByOrderId(order.getId());
        if (existing != null) return existing;
        bill value = new bill(nextBillId++, order, taxRate);
        repository.save(value);
        return value;
    }
}
