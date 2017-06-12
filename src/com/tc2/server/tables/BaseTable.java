package com.tc2.server.tables;

import com.tc2.database.Database;
import com.tc2.database.Table;
import com.tc2.server.service.DatabaseService;

public class BaseTable extends Table {
    protected final DatabaseService service;
    protected final Database database;

    public BaseTable(DatabaseService service) {
        super(service.database);

        this.service = service;
        this.database = service.database;
    }
}
