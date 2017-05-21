package com.tc2.database.expr;

import com.tc2.database.TableName;

public class UPDATE implements Expression {
    public final TableName tableName;
    public final EQS eqs;

    public UPDATE(TableName tableName, EQS eqs) {
        this.tableName = tableName;
        this.eqs = eqs;
    }

    @Override
    public String toSQL() {
        return "update " + tableName.name + " set " + eqs.toSQL();
    }
}
