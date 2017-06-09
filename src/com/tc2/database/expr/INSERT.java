package com.tc2.database.expr;

import com.tc2.database.Database;
import com.tc2.database.TableName;
import com.tc2.toolkit.helper.StringBuilderHelper;

public class INSERT implements Expression {
    private TableName tableName;
    private EQ[] eqs;

    public INSERT(TableName tableName, EQ... eqs) {
        this.tableName = tableName;
        this.eqs = eqs;
    }

    @Override
    public String toSQL() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (EQ eq : eqs) {
            sb1.append("`" + eq.defined.name + "`").append(", ");
        }
        for (EQ eq : eqs) {
            sb2.append(Database.convertValue(eq.value) + ", ");
        }
        StringBuilderHelper.removeLast(sb1, 2);
        StringBuilderHelper.removeLast(sb2, 2);
        return "insert into `" + tableName.name + "`(" + sb1 + ") values(" + sb2 + ")";
    }
}
