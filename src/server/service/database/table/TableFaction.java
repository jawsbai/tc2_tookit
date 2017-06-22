package server.service.database.table;

import rpc2j_table.Faction;
import server.service.database.DatabaseService;
import toolkit.database.expr.ORDER_BY;
import toolkit.database.expr.WHERE;

public class TableFaction extends ServiceTable {
    public TableFaction(DatabaseService service) {
        super(service, Faction.newTableDefined());
    }

    public Faction findFaction(int id) {
        final Faction[] faction = new Faction[1];
        select(new WHERE(Faction.FD_FACTIONID.eq(id)), new ORDER_BY(), 1, rs -> {
            faction[0] = Faction.newFromRS(rs);
        });
        return faction[0];
    }

    public boolean createFaction(Faction table) {
        return insert(table.toEQS());
    }
}
