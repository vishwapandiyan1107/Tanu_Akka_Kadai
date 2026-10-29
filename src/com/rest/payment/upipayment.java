package com.rest.payment;

public class upipayment implements paymentmethod {
    @Override
    public void pay(double amount) {
        System.out.println("UPI payment of rs." + amount + " successful");
    }

    @Override
    public String getMethodname() {
        return "UPI";
    }
}
