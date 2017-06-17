package toolkit.database.expr;

import toolkit.database.Database;
import toolkit.database.FieldDefined;

public class EQ implements Expression {
    public final FieldDefined defined;
    public final Object value;

    public EQ(FieldDefined defined, Object value) {
        this.defined = defined;
        this.value = value;
    }

    @Override
    public String toSQL() {
        return "`" + defined.name + "`=" + Database.convertValue(value);
    }
}
