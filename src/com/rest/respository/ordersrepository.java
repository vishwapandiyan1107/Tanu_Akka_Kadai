package com.rest.respository;

import com.rest.models.orders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ordersrepository {
    private final List<orders> ordersList = new ArrayList<>();

    public void save(orders order) {
        ordersList.add(order);
    }

    public List<orders> getAll() {
        return Collections.unmodifiableList(ordersList);
    }
}
