package toolkit.database.expr;

import toolkit.database.Database;
import toolkit.database.FieldDefined;

import java.util.ArrayList;

public class EQ implements Expression {
    public final FieldDefined defined;
    public final Object value;

    public EQ(FieldDefined defined, Object value) {
        this.defined = defined;
        this.value = value;
    }

    private static boolean findField(FieldDefined[] fields, FieldDefined findField) {
        for (FieldDefined field : fields) {
            if (field == findField) {
                return true;
            }
        }
        return false;
    }

    public static EQ[] ignoreFields(EQ[] eqs, FieldDefined... fields) {
        ArrayList<EQ> list = new ArrayList<>();
        for (EQ eq : eqs) {
            if (!findField(fields, eq.defined)) {
                list.add(eq);
            }
        }
        EQ[] newEqs = new EQ[list.size()];
        for (int i = 0, len = list.size(); i < len; i++) {
            newEqs[i] = list.get(i);
        }
        return newEqs;
    }

    @Override
    public String toSQL() {
        return "`" + defined.name + "`=" + Database.convertValue(value);
    }
}
