package server.table;

import rpc2j_table.OperationLog;
import server.service.DatabaseService;
import toolkit.database.expr.EQ;

public class TableOperationLog extends ServiceTable {
    public TableOperationLog(DatabaseService service, String tableName) {
        super(service, OperationLog.newTableDefined(tableName));
    }

    public boolean insert(OperationLog table) {
        return insert(EQ.ignoreFields(table.toEQS(), OperationLog.FD_ID));
    }
}
