package toolkit.database.fields.base;

import toolkit.database.FieldDefined;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VARCHAR extends FieldDefined {
    public final int len;

    public VARCHAR(String name, boolean notNull, int len) {
        super(name, VARCHAR.class.getSimpleName(), notNull);

        this.len = len;
    }

    @Override
    public String fieldSQL() {
        return "`" + name + "`" +
                " " + type + "(" + len + ")" +
                (notNull ? " NOT NULL" : "");
    }
}
