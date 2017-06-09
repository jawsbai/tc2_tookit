package com.tc2.database.expr;

public class WHERE implements Expression {
    private Expression expr;
    private StringBuilder conds = new StringBuilder();

    public WHERE() {
    }

    public WHERE(Expression expr) {
        this.expr = expr;
    }

    public WHERE and(Expression expr) {
        conds.append(" and " + expr.toSQL());
        return this;
    }

    public WHERE or(Expression expr) {
        conds.append(" or " + expr.toSQL());
        return this;
    }

    @Override
    public String toSQL() {
        if (this.expr == null) {
            return "";
        }
        return "where " + expr.toSQL() + conds;
    }
}
