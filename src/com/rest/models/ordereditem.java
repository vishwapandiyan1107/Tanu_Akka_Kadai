package com.rest.models;

import java.util.Objects;

public class ordereditem {
    private final menuitem item;
    private int quantity;

    public ordereditem(menuitem item, int quantity) {
        this.item = Objects.requireNonNull(item, "Menu item cannot be null.");
        setQuantity(quantity);
    }

    public menuitem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return item.getPrice() * quantity;
    }
}
