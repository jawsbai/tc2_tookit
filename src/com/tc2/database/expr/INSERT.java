package com.tc2.database.expr;

import com.tc2.database.Database;
import com.tc2.database.FieldValue;
import com.tc2.database.TableName;
import com.tc2.toolkit.utils.StringBuilderHelper;

public class INSERT implements Expression {
    public final TableName tableName;
    public final FieldValue[] values;

    public INSERT(TableName tableName, FieldValue... values) {
        this.tableName = tableName;
        this.values = values;
    }

    @Override
    public String toSQL() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (FieldValue value : values) {
            sb1.append("`" + value.defined.name + "`").append(", ");
        }
        for (FieldValue value : values) {
            sb2.append(Database.convertValue(value.value) + ", ");
        }
        StringBuilderHelper.removeLast(sb1, 2);
        StringBuilderHelper.removeLast(sb2, 2);
        return "insert into `" + tableName.name + "`(" + sb1 + ") values(" + sb2 + ")";
    }
}
