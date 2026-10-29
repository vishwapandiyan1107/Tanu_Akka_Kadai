package com.rest.cli;

import java.util.Scanner;

public class restaurantcli {
    private final tablecli tableCli;
    private final menuitemcli menuCli;
    private final orderscli orderCli;
    private final Scanner scanner;

    public restaurantcli(tablecli tableCli, menuitemcli menuCli,
                         orderscli orderCli, Scanner scanner) {
        this.tableCli = tableCli;
        this.menuCli = menuCli;
        this.orderCli = orderCli;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== RESTAURANT ===");
            System.out.println("1. View tables");
            System.out.println("2. View menu");
            System.out.println("3. Take an order");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    tableCli.showTables();
                    break;
                case "2":
                    menuCli.showMenu();
                    break;
                case "3":
                    orderCli.takeOrder();
                    break;
                case "0":
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 0.");
            }
        }
    }
}
