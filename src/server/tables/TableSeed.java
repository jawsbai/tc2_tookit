package server.tables;

import rpc2j_table.Seed;
import server.service.DatabaseService;
import toolkit.database.expr.EQ;
import toolkit.database.expr.ORDER_BY;
import toolkit.database.expr.WHERE;

import java.util.Date;

public class TableSeed extends ServiceTable {
    private final String prefix;

    public TableSeed(DatabaseService service, String prefix) {
        super(service, Seed.newTableDefined());

        this.prefix = prefix;
    }

    private int findIndex(String key) {
        final int[] index = {0};
        select(new WHERE(Seed.FD_KEY.eq(key)), new ORDER_BY(), 1, rs -> {
            index[0] = Seed.FD_INDEX.getValue(rs);
        });
        return index[0];
    }

    public String newId(String key) {
        EQ keyEQ = Seed.FD_KEY.eq(key);
        WHERE where = new WHERE(keyEQ);

        int index = findIndex(key);

        boolean success;
        if (index == 0) {
            index = 1;
            success = insert(
                    keyEQ,
                    Seed.FD_INDEX.eq(index),
                    Seed.FD_CREATETIME.eqNow(),
                    Seed.FD_UPDATETIME.eq(new Date()));
        } else {
            index++;
            success = update(new EQ[]{
                    Seed.FD_INDEX.eq(index),
                    Seed.FD_UPDATETIME.eqNow()}, where);
        }

        return success ? prefix + key + index : null;
    }
}
