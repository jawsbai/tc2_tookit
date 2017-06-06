package com.tc2.database.expr;

import com.tc2.database.TableName;

import java.lang.reflect.Field;

public class UPDATE implements Expression {
    private TableName tableName;
    private Expressions eqs;

    public UPDATE(TableName tableName, EQ... eqs) {
        this.tableName = tableName;
        this.eqs = new Expressions(eqs, ", ");
    }

    @Override
    public String toSQL() {
        return "update " + tableName.name + " set " + eqs.toSQL();
    }
}
