package com.tc2.database.expr;

import com.tc2.database.FieldDefined;

public abstract class FieldExpression extends Expression {
    public final FieldDefined defined;
    public final Object value;

    public FieldExpression(FieldDefined defined, Object value) {
        this.defined = defined;
        this.value = value;
    }
}
