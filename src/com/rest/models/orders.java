package com.rest.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class orders {
    public enum Status {
        OPEN,
        COMPLETED,
        CANCELLED
    }

    private final int id;
    private final int tableId;
    private final List<ordereditem> items = new ArrayList<>();
    private Status status;

    public orders(int id, int tableId) {
        if (id <= 0) {
            throw new IllegalArgumentException("Order ID must be positive.");
        }
        if (tableId <= 0) {
            throw new IllegalArgumentException("Table ID must be positive.");
        }

        this.id = id;
        this.tableId = tableId;
        this.status = Status.OPEN;
    }

    public int getId() {
        return id;
    }

    public int getTableId() {
        return tableId;
    }

    public List<ordereditem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Status getStatus() {
        return status;
    }

    public void addItem(ordereditem item) {
        if (status != Status.OPEN) {
            throw new IllegalStateException("Items can only be added to open orders.");
        }
        items.add(Objects.requireNonNull(item, "Ordered item cannot be null."));
    }

    public double getTotal() {
        return items.stream().mapToDouble(ordereditem::getSubtotal).sum();
    }

    public void complete() {
        if (status != Status.OPEN) {
            throw new IllegalStateException("Only open orders can be completed.");
        }
        status = Status.COMPLETED;
    }

    public void cancel() {
        if (status != Status.OPEN) {
            throw new IllegalStateException("Only open orders can be cancelled.");
        }
        status = Status.CANCELLED;
    }
}
