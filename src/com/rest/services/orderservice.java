package com.rest.services;

import com.rest.models.menuitem;
import com.rest.models.ordereditem;
import com.rest.models.orders;
import com.rest.respository.ordersrepository;
import java.util.List;

public class orderservice {
    private final ordersrepository orderRepository;
    private final menuservice menuService;
    private int nextOrderId = 1;

    public orderservice(ordersrepository orderRepository, menuservice menuService) {
        this.orderRepository = orderRepository;
        this.menuService = menuService;
    }

    public orders createOrder(int tableId) {
        orders order = new orders(nextOrderId++, tableId);
        orderRepository.save(order);
        return order;
    }

    public void addItem(orders order, int menuItemId, int quantity) {
        menuitem selected = menuService.getMenu().stream()
                .filter(item -> item.getId() == menuItemId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found."));
        order.addItem(new ordereditem(selected, quantity));
    }

    public List<orders> getAllOrders() {
        return orderRepository.getAll();
    }

    public orders getOrder(int orderId) {
        return orderRepository.getAll().stream()
                .filter(order -> order.getId() == orderId)
                .findFirst()
                .orElse(null);
    }
}
