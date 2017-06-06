package com.tc2.database.expr;

import com.tc2.database.Database;
import com.tc2.database.FieldDefined;

public class EQ implements Expression {
    public final FieldDefined defined;
    public final Object value;

    public EQ(FieldDefined defined, Object value) {
        this.defined = defined;
        this.value = value;
    }

    @Override
    public String toSQL() {
        return "`" + defined.name + "`=" + Database.convertValue(value);
    }
}
