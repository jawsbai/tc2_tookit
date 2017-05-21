package com.tc2.database.expr;

import com.tc2.database.Database;
import com.tc2.database.FieldDefined;
import com.tc2.database.FieldValue;

public class EQ extends FieldValue implements Expression {
    public EQ(FieldDefined defined, Object value) {
        super(defined, value);
    }

    @Override
    public String toSQL() {
        return "`" + defined.name + "`=" + Database.convertValue(value);
    }
}
