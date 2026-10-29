package com.rest.payment;

public class cashpayment implements paymentmethod {
    @Override
    public void pay(double amount) {
       System.out.println("Cash payment of rs." + amount + " successful");
    }

    @Override
    public String getMethodname() {
        return "Cash";
    }
}
