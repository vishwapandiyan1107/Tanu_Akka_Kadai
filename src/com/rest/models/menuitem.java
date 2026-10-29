package com.rest.models;

import java.util.Objects;

public class menuitem {
    private final int id;
    private final String name;
    private final double price;

    public menuitem(int id, String name, double price) {
        if (id <= 0) {
            throw new IllegalArgumentException("Menu item ID must be positive.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        this.id = id;
        this.name = Objects.requireNonNull(name, "Name cannot be null.").trim();
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
