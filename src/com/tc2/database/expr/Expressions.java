package com.tc2.database.expr;

import com.tc2.toolkit.helper.StringBuilderHelper;

public class Expressions implements Expression {
    public final Expression[] exprs;
    public final String separator;

    public Expressions(Expression[] exprs, String separator) {
        this.exprs = exprs;
        this.separator = separator;
    }

    @Override
    public String toSQL() {
        StringBuilder sb = new StringBuilder();
        for (Expression expr : exprs) {
            sb.append(expr.toSQL()).append(this.separator);
        }
        StringBuilderHelper.removeLast(sb, this.separator.length());
        return sb.toString();
    }
}
