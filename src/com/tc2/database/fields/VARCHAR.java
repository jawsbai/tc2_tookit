package com.tc2.database.fields;

import com.tc2.database.FieldDefined;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VARCHAR extends FieldDefined<String> {
    public final int len;

    public VARCHAR(String name, boolean notNull, int len) {
        super(name, VARCHAR.class.getSimpleName(), notNull);

        this.len = len;
    }

    public VARCHAR(String name, int len) {
        this(name, true, len);
    }

    @Override
    public String fieldSQL() {
        return "`" + name + "`" +
                " " + type + "(" + len + ")" +
                (notNull ? " NOT NULL" : "");
    }

    @Override
    public String getRSValue(ResultSet rs) {
        try {
            return rs.getString(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
