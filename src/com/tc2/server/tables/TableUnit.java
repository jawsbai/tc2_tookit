package com.tc2.server.tables;

import com.tc2.database.fields.INT;
import com.tc2.database.fields.JSON;
import com.tc2.database.fields.TIME;
import com.tc2.database.fields.VARCHAR;
import com.tc2.server.service.DatabaseService;
import com.tc2.toolkit.print.Console;

public class TableUnit extends BaseTable {
    private VARCHAR FD_UNIT_ID;
    private VARCHAR FD_HERO_ID;
    private VARCHAR FD_NAME;
    private TIME FD_CREATE_TIME;

    private VARCHAR FD_DATA_ID;
    private JSON FD_EQUIPS;
    private INT FD_LEVEL;
    private INT FD_EXP;
    private INT FD_HEALTH;
    private INT FD_DEATHS;

    public TableUnit(DatabaseService service) {
        super(service);
    }

    public void findUnitsByHeroId(String heroId) {

    }

    public boolean createUnit(String heroId,
                              String name,
                              String dataId,
                              String[] equips,
                              int level,
                              int exp,
                              int health,
                              int deaths) {
        return insert(
                FD_UNIT_ID.eq(service.newId("unit")),
                FD_HERO_ID.eq(heroId),
                FD_NAME.eq(name),
                FD_CREATE_TIME.eqNow(),
                FD_DATA_ID.eq(dataId),
                FD_EQUIPS.eqJSON(equips),
                FD_LEVEL.eq(level),
                FD_EXP.eq(exp),
                FD_HEALTH.eq(health),
                FD_DEATHS.eq(deaths)
        );
    }
}
