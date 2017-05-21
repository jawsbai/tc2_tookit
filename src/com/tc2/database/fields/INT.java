package com.tc2.database.fields;

import com.tc2.database.FieldDefined;

public class INT extends FieldDefined<Integer> {
    public final int len;
    public final boolean unsigned;
    public final boolean autoIncrement;

    public INT(String name, boolean notNull, int len, boolean unsigned, boolean autoIncrement) {
        super(name, INT.class.getSimpleName(), notNull);

        this.len = len;
        this.unsigned = unsigned;
        this.autoIncrement = autoIncrement;
    }

    public INT(String name, boolean autoIncrement) {
        this(name, true, 11, true, autoIncrement);
    }

    public INT(String name) {
        this(name, true, 11, true, false);
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
