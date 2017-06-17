package toolkit.database.fields.base;

import toolkit.database.FieldDefined;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TEXT extends FieldDefined {
    public TEXT(String name, boolean notNull) {
        super(name, TEXT.class.getSimpleName(), notNull);
    }

    @Override
    public String fieldSQL() {
        return "`" + name + "`" +
                " " + type +
                (notNull ? " NOT NULL" : "");
    }
}
