package server.table;

import rpc2j_table.Planet;
import server.service.DatabaseService;
import toolkit.database.expr.ORDER_BY;
import toolkit.database.expr.WHERE;

public class TablePlanet extends ServiceTable {
    public TablePlanet(DatabaseService service) {
        super(service, Planet.newTableDefined());
    }

    public Planet findPlanet(int id) {
        final Planet[] planet = new Planet[1];
        select(new WHERE(Planet.FD_PLANETID.eq(id)), new ORDER_BY(), 1, rs -> {
            planet[0] = Planet.newFromRS(rs);
        });
        return planet[0];
    }

    public boolean createPlanet(Planet table) {
        return insert(table.toEQS());
    }
}
