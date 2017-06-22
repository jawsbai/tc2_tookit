package server.service.database.table;

import rpc2j_table.Galaxy;
import server.service.database.DatabaseService;
import toolkit.database.expr.ORDER_BY;
import toolkit.database.expr.WHERE;

public class TableGalaxy extends ServiceTable {
    public TableGalaxy(DatabaseService service) {
        super(service, Galaxy.newTableDefined());
    }

    public Galaxy findGalaxy(int id) {
        final Galaxy[] galaxy = new Galaxy[1];
        select(new WHERE(Galaxy.FD_GALAXYID.eq(id)), new ORDER_BY(), 1, rs -> {
            galaxy[0] = Galaxy.newFromRS(rs);
        });
        return galaxy[0];
    }

    public boolean createGalaxy(Galaxy table) {
        return insert(table.toEQS());
    }
}
