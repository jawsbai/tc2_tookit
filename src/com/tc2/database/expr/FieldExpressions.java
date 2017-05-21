package com.tc2.database.expr;

import com.tc2.toolkit.utils.StringBuilderHelper;

public class FieldExpressions extends Expression {
    public final FieldExpression[] exprs;

    public FieldExpressions(FieldExpression... exprs) {
        this.exprs = exprs;
    }

    @Override
    public String toSQL() {
        StringBuilder sb = new StringBuilder();
        for (FieldExpression expr : exprs) {
            sb.append(expr.toSQL()).append(", ");
        }
        StringBuilderHelper.removeLast(sb, 2);
        return sb.toString();
    }
}
