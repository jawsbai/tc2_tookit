package toolkit.database;

import toolkit.database.expr.EQ;

import java.sql.ResultSet;

public abstract class FieldDefined {
    public final String name;
    public final String type;
    public final boolean notNull;

    public FieldDefined(String name, String type, boolean notNull) {
        this.name = name;
        this.type = type;
        this.notNull = notNull;
    }

    public abstract String fieldSQL();

    @Override
    public String toString() {
        return name;
    }
}
