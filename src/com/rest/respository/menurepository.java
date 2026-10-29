package com.rest.respository;

import com.rest.models.menuitem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class menurepository {
    private final List<menuitem> items = new ArrayList<>();

    public menurepository() {
        items.add(new menuitem(1, "Coffee", 120.00));
        items.add(new menuitem(2, "Sandwich", 180.00));
        items.add(new menuitem(3, "Salad", 160.00));
        items.add(new menuitem(4, "Pasta", 250.00));
    }

    public List<menuitem> getAll() {
        return Collections.unmodifiableList(items);
    }
}
