package com.tc2.galaxy.tables;

import com.tc2.database.*;
import com.tc2.database.fields.INT;
import com.tc2.database.fields.VARCHAR;

public class TableHero extends Table {
    public static final TableName TABLE_NAME = new TableName(TableHero.class.getSimpleName());
    public static final INT FD_HERO_ID = new INT("hero_id");
    public static final VARCHAR FD_USER_ID = new VARCHAR("user_id", 50);
    public static final VARCHAR FD_HERO_NAME = new VARCHAR("hero_name", 50);
    public static final VARCHAR FD_CREATE_TIME = new VARCHAR("create_time", 50);

    public static final TableDefined TABLE_DEFINED = new TableDefined(
            TABLE_NAME,
            FD_HERO_ID,
            FD_USER_ID,
            FD_HERO_NAME,
            FD_CREATE_TIME
    );

    public TableHero(Database database) {
        super(database, TABLE_DEFINED);
    }
}
