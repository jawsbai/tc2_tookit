package server.service.database.table;

import server.gencode.table.Territory;
import server.service.database.DatabaseService;
import toolkit.database.expr.ORDER_BY;
import toolkit.database.expr.WHERE;

public class TableTerritory extends ServiceTable {
    public TableTerritory(DatabaseService service) {
        super(service, Territory.newTableDefined());
    }

    public Territory findTerritory(int terrId) {
        final Territory[] territory = new Territory[1];
        select(new WHERE(Territory.FD_TERRID.eq(terrId)), new ORDER_BY(), 1, rs -> {
            territory[0] = Territory.newFromRS(rs);
        });
        return territory[0];
    }

    public boolean createTerritory(Territory table) {
        return insert(table.toEQS());
    }
}
