package com.tc2.database.expr;

public class WHERE implements Expression {

    @Override
    public String toSQL() {
        return "where";
    }
}
