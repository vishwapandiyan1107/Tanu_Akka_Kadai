package com.rest.payment;

public class cardpayment implements paymentmethod {
    @Override
    public void pay(double amount) {
        System.out.println("Card payment of rs." + amount + " successful");
    }

    @Override
    public String getMethodname() {
        return "Card";
    }           
    
}
