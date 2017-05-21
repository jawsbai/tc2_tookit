package com.tc2.database.fields;

import com.tc2.database.FieldDefined;

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
}
