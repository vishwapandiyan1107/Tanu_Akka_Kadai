package com.rest.services;

import com.rest.models.menuitem;
import com.rest.respository.menurepository;
import java.util.List;

public class menuservice {
    private final menurepository repository;

    public menuservice(menurepository repository) {
        this.repository = repository;
    }

    public List<menuitem> getMenu() {
        return repository.getAll();
    }
}
