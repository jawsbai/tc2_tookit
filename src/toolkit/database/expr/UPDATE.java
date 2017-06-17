package toolkit.database.expr;

import toolkit.database.TableName;

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
