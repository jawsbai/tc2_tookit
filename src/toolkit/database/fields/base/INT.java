package toolkit.database.fields.base;

import toolkit.database.FieldDefined;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class INT extends FieldDefined {
    public final int len;
    public final boolean unsigned;
    public final boolean autoIncrement;

    public INT(String name, boolean notNull, int len, boolean unsigned, boolean autoIncrement) {
        super(name, INT.class.getSimpleName(), notNull);

        this.len = len;
        this.unsigned = unsigned;
        this.autoIncrement = autoIncrement;
    }

    @Override
    public String fieldSQL() {
        return "`" + name + "`" +
                " " + type + "(" + len + ")" +
                (unsigned ? " UNSIGNED" : "") +
                (notNull ? " NOT NULL" : "") +
                (autoIncrement ? " AUTO_INCREMENT" : "");
    }

    @Override
    public String toString() {
        return "INT{" +
                "len=" + len +
                ", unsigned=" + unsigned +
                ", autoIncrement=" + autoIncrement +
                '}';
    }
}
