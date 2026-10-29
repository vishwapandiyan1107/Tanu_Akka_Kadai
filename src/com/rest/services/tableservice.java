package com.rest.services;

import com.rest.models.TABLE;
import com.rest.respository.tablerespository;
import java.util.List;

public class tableservice {
    private final tablerespository repository;

    public tableservice(tablerespository repository){
        this.repository = repository;
    }

    public List<TABLE> getAllTables() {
        return repository.getTables();
    }
}
