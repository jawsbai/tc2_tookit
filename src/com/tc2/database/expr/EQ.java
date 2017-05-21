package com.tc2.database.expr;

import com.tc2.database.Database;
import com.tc2.database.FieldDefined;

import java.util.Objects;

public class EQ extends FieldExpression {
    public EQ(FieldDefined defined, Object value) {
        super(defined, value);
    }

    @Override
    public String toSQL() {
        return "`" + defined.name + "`=" + Database.convertValue(value);
    }
}
