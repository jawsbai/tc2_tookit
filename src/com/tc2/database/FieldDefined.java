package com.tc2.database;

import com.tc2.database.expr.EQ;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FieldDefined<T> {
    public final String name;
    public final String type;
    public final boolean notNull;

    public FieldDefined(String name, String type, boolean notNull) {
        this.name = name;
        this.type = type;
        this.notNull = notNull;
    }

    public abstract String fieldSQL();

    public abstract T getRSValue(ResultSet rs);

    public EQ eq(T value) {
        return new EQ(this, value);
    }

    @Override
    public String toString() {
        return name;
    }
}
