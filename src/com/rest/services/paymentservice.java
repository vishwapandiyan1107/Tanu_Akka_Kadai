package com.rest.services;

import com.rest.models.orders;
import com.rest.models.bill;
import com.rest.models.payment;
import com.rest.respository.paymentrepository;

public class paymentservice {
    private final paymentrepository repository;
    private int nextPaymentId = 1;

    public paymentservice(paymentrepository repository) {
        if (repository == null) throw new IllegalArgumentException("Payment repository is required.");
        this.repository = repository;
    }

    public payment pay(orders order, bill bill, payment.Method method) {
        if (order == null) throw new IllegalArgumentException("Order was not found.");
        if (bill == null) throw new IllegalArgumentException("Bill is required before payment.");
        if (bill.getOrderId() != order.getId()) throw new IllegalArgumentException("Bill does not belong to this order.");
        if (order.getStatus() != orders.Status.OPEN) throw new IllegalStateException("This order has already been completed or cancelled.");
        if (order.getItems().isEmpty()) throw new IllegalStateException("Cannot pay for an empty order.");
        payment value = new payment(nextPaymentId++, order.getId(), bill.getId(), bill.getTotal(), method);
        repository.save(value);
        order.complete();
        return value;
    }

    public payment pay(orders order, payment.Method method) {
        if (order == null) {
            throw new IllegalArgumentException("Order was not found.");
        }
        if (order.getStatus() != orders.Status.OPEN) {
            throw new IllegalStateException("This order has already been completed or cancelled.");
        }
        if (order.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot pay for an empty order.");
        }

        return pay(order, new bill(1, order, 0.0), method);
    }

}


        







