package com.rest.cli;

import com.rest.models.menuitem;
import com.rest.services.menuservice;
import java.util.Scanner;

public class menuitemcli {
    private final menuservice service;
    private final Scanner scanner;

    public menuitemcli(menuservice service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void showMenu() {
        System.out.println("\n=== MENU ===");
        for (menuitem item : service.getMenu()) {
            System.out.printf("%d. %s - %.2f%n", item.getId(), item.getName(), item.getPrice());
        }
    }

    public menuitem chooseMenuItem() {
        while (true) {
            showMenu();
            System.out.println("0. Finish order");
            System.out.print("Choose a menu item: ");
            String input = scanner.nextLine().trim();

            int itemId;
            try {
                itemId = Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid menu item number.");
                continue;
            }
            if (itemId == 0) {
                return null;
            }

            for (menuitem item : service.getMenu()) {
                if (item.getId() == itemId) {
                    return item;
                }
            }
            System.out.println("That menu item does not exist.");
        }
    }
}
