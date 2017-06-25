package server.service.database.table;

import server.gencode.table.Unit;
import server.service.database.DatabaseService;
import toolkit.database.expr.ORDER_BY;
import toolkit.database.expr.WHERE;

import java.util.ArrayList;

public class TableUnit extends ServiceTable {

    public TableUnit(DatabaseService service) {
        super(service, Unit.newTableDefined());
    }

    public ArrayList<Unit> findUnitsByHeroId(String heroId) {
        ArrayList<Unit> list = new ArrayList<>();
        select(new WHERE(Unit.FD_HEROID.eq(heroId)), new ORDER_BY(Unit.FD_CREATETIME), rs -> {
            list.add(Unit.newFromRS(rs));
        });
        return list;
    }

    public boolean createUnit(Unit table) {
        table.setUnitId(Unit.class.getSimpleName());
        return insert(table.toEQS());
    }
}
