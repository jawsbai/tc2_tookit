package server.service.database.table;

import server.service.database.DatabaseService;
import toolkit.database.Database;
import toolkit.database.Table;
import toolkit.database.TableDefined;

public class ServiceTable extends Table {
    protected final DatabaseService service;
    protected final Database database;

    public ServiceTable(DatabaseService service, TableDefined tableDefined) {
        super(service.database, tableDefined);

        this.service = service;
        this.database = service.database;
    }
}
