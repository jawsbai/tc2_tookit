package com.tc2.server.tables;

import com.tc2.database.expr.EQ;
import com.tc2.database.expr.ORDER_BY;
import com.tc2.database.expr.WHERE;
import com.tc2.database.fields.INT;
import com.tc2.database.fields.TIME;
import com.tc2.database.fields.VARCHAR;
import com.tc2.server.service.DatabaseService;

public class TableSeed extends BaseTable {
    private VARCHAR FD_KEY;
    private INT FD_INDEX;
    private TIME FD_CREATE_TIME;
    private TIME FD_UPDATE_TIME;

    private final String prefix;

    public TableSeed(DatabaseService service, String prefix) {
        super(service);

        this.prefix = prefix;
    }

    private int findIndex(String key) {
        final int[] index = {0};
        select(new WHERE(FD_KEY.eq(key)), new ORDER_BY(), 1, rs -> {
            index[0] = FD_INDEX.getValue(rs);
        });
        return index[0];
    }

    public String newId(String key) {
        EQ keyEQ = FD_KEY.eq(key);
        WHERE where = new WHERE(keyEQ);

        int index = findIndex(key);

        boolean success;
        if (index == 0) {
            index = 1;
            success = insert(
                    keyEQ,
                    FD_INDEX.eq(index),
                    FD_CREATE_TIME.eqNow(),
                    FD_UPDATE_TIME.eq(0));
        } else {
            index++;
            success = update(new EQ[]{
                    FD_INDEX.eq(index),
                    FD_UPDATE_TIME.eqNow()}, where);
        }

        return success ? prefix + key + index : null;
    }
}
