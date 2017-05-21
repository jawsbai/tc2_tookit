package galaxy.tables;

import com.tc2.database.*;
import com.tc2.database.fields.INT;
import com.tc2.database.fields.VARCHAR;

public class TableHero extends Table {
    public static final TableName TABLE_NAME = new TableName(TableHero.class.getSimpleName());
    public static final INT HERO_ID = new INT("hero_id");
    public static final VARCHAR USER_ID = new VARCHAR("player_id", 50);
    public static final VARCHAR HERO_NAME = new VARCHAR("hero_name", 50);
    public static final VARCHAR CREATE_TIME = new VARCHAR("create_time", 50);

    public static final TableDefined TABLE_DEFINED = new TableDefined(
            TABLE_NAME, HERO_ID.name,
            HERO_ID,
            USER_ID,
            HERO_NAME,
            CREATE_TIME
    );

    public TableHero(Database database) {
        super(database, TABLE_DEFINED);
    }
}
