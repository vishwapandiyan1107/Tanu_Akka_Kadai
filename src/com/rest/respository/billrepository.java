package com.rest.respository;

import com.rest.models.bill;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class billrepository {
    private final List<bill> bills = new ArrayList<>();

    public void save(bill value) {
        if (value == null) throw new IllegalArgumentException("Bill is required.");
        bills.add(value);
    }

    public List<bill> getAll() { return Collections.unmodifiableList(bills); }

    public bill findById(int id) {
        for (bill value : bills) if (value.getId() == id) return value;
        return null;
    }

    public bill findByOrderId(int orderId) {
        for (bill value : bills) if (value.getOrderId() == orderId) return value;
        return null;
    }
}
