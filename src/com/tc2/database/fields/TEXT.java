package com.tc2.database.fields;

import com.tc2.database.FieldDefined;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TEXT extends FieldDefined<String> {
    public TEXT(String name, boolean notNull) {
        super(name, TEXT.class.getSimpleName(), notNull);
    }

    public TEXT(String name) {
        this(name, true);
    }

    @Override
    public String fieldSQL() {
        return "`" + name + "`" +
                " " + type +
                (notNull ? " NOT NULL" : "");
    }

    @Override
    public String getValue(ResultSet rs) {
        try {
            return rs.getString(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
