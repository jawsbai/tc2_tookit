package server.service.database.table;

import server.gencode.table.Planet;
import server.service.database.DatabaseService;
import toolkit.database.expr.ORDER_BY;
import toolkit.database.expr.WHERE;

public class TablePlanet extends ServiceTable {
    public TablePlanet(DatabaseService service) {
        super(service, Planet.newTableDefined());
    }

    public Planet findPlanet(int planetId) {
        final Planet[] planet = new Planet[1];
        select(new WHERE(Planet.FD_PLANETID.eq(planetId)), new ORDER_BY(), 1, rs -> {
            planet[0] = Planet.newFromRS(rs);
        });
        return planet[0];
    }

    public boolean createPlanet(Planet table) {
        return insert(table.toEQS());
    }
}
