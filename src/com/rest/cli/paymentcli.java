package com.rest.cli;

import com.rest.payment.cardpayment;
import com.rest.payment.cashpayment;
import com.rest.payment.paymentmethod;
import com.rest.payment.upipayment;
import com.rest.models.orders;
import com.rest.models.payment;
import com.rest.models.bill;
import com.rest.services.paymentservice;
import com.rest.services.billservice;

import java.util.Scanner;

public class paymentcli {
    private final paymentservice paymentService;
    private final Scanner scanner;
    private final billservice billService;

    public paymentcli(paymentservice paymentService, Scanner scanner) {
        this(paymentService, new billservice(new com.rest.respository.billrepository()), scanner);
    }

    public paymentcli(paymentservice paymentService, billservice billService, Scanner scanner) {
        this.paymentService = paymentService;
        this.billService = billService;
        this.scanner = scanner;
    }

    public void processPayment(orders order) {
        bill bill = billService.generateBill(order);
        System.out.printf("\nBill %d for order %d:%n", bill.getId(), bill.getOrderId());
        for (com.rest.models.ordereditem item : bill.getItems()) {
            System.out.printf("%s x%d - %.2f%n", item.getItem().getName(), item.getQuantity(), item.getSubtotal());
        }
        System.out.printf("Subtotal: %.2f%nTax: %.2f%nAmount due: %.2f%n", bill.getSubtotal(), bill.getTax(), bill.getTotal());
        System.out.println("Select a payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            System.out.println("Invalid choice. Payment cancelled.");
            return;
        }
        paymentmethod paymentMethod;

        switch (choice) {
            case 1:
                paymentMethod = new cashpayment();
                break;
            case 2:
                paymentMethod = new cardpayment();
                break;
            case 3:
                paymentMethod = new upipayment();
                break;
            default:
                System.out.println("Invalid choice. Payment failed.");
                return;
        }

        payment.Method method;
        if (paymentMethod instanceof cashpayment) {
            method = payment.Method.CASH;
        } else if (paymentMethod instanceof cardpayment) {
            method = payment.Method.CARD;
        } else {
            method = payment.Method.UPI;
        }

        try {
            paymentService.pay(order, bill, method);
            paymentMethod.pay(bill.getTotal());
        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.out.println("Payment failed: " + exception.getMessage());
        }
    }
    
}
