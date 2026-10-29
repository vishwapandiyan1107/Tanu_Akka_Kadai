package com.rest.cli;

import com.rest.models.TABLE;
import com.rest.services.tableservice;
import java.util.List;

public class tablecli {
    private final tableservice service;

    public tablecli(tableservice service) {
        this.service = service;
    }

    public void showTables() {
        List<TABLE> tables = service.getAllTables();
        System.out.println("\n=== TABLES ===");
        for (TABLE table : tables) {
            System.out.printf("Table %d - capacity: %d - status: %s%n",
                    table.getid(), table.getcapacity(), table.getstatus());
        }
    }
}
