package com.rest.cli;

import com.rest.models.menuitem;
import com.rest.models.ordereditem;
import com.rest.models.orders;
import com.rest.services.orderservice;
import com.rest.services.tableservice;
import java.util.Scanner;

public class orderscli {
    private final tableservice tableService;
    private final orderservice orderService;
    private final menuitemcli menuCli;
    private final paymentcli paymentCli;
    private final Scanner scanner;

    public orderscli(tableservice tableService, orderservice orderService,
                     menuitemcli menuCli, paymentcli paymentCli, Scanner scanner) {
        this.tableService = tableService;
        this.orderService = orderService;
        this.menuCli = menuCli;
        this.paymentCli = paymentCli;
        this.scanner = scanner;
    }

    public void takeOrder() {
        System.out.print("Enter table ID for this order: ");
        int tableId;
        try {
            tableId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a valid table ID.");
            return;
        }

        boolean tableExists = tableService.getAllTables().stream()
                .anyMatch(table -> table.getid() == tableId);
        if (!tableExists) {
            System.out.println("Table " + tableId + " does not exist.");
            return;
        }

        orders order = orderService.createOrder(tableId);
        while (true) {
            menuitem selectedItem = menuCli.chooseMenuItem();
            if (selectedItem == null) {
                break;
            }

            System.out.print("Enter quantity: ");
            try {
                int quantity = Integer.parseInt(scanner.nextLine().trim());
                orderService.addItem(order, selectedItem.getId(), quantity);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a whole number for quantity.");
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }

        if (order.getItems().isEmpty()) {
            System.out.println("No items were added; order was not created.");
            return;
        }

        printOrder(order);
        paymentCli.processPayment(order);
    }

    private void printOrder(orders order) {
        System.out.printf("\nOrder %d for table %d:%n", order.getId(), order.getTableId());
        for (ordereditem item : order.getItems()) {
            System.out.printf("%s x%d - %.2f%n", item.getItem().getName(),
                    item.getQuantity(), item.getSubtotal());
        }
        System.out.printf("Total: %.2f%n", order.getTotal());
    }
}
