package com.tc2.database.fields;

import com.tc2.database.FieldDefined;

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
}
