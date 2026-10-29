package com.rest.respository;

import com.rest.models.TABLE;
import java.util.ArrayList;
import java.util.List;

public class tablerespository {

    private final List<TABLE> tables = new ArrayList<>();

    public tablerespository() {
        tables.add(new TABLE(1, 2));
        tables.add(new TABLE(2, 4));
        tables.add(new TABLE(3, 6));
    }

    public List<TABLE> getTables() {
        return tables;
    }
}
