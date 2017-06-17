package toolkit.database.fields;

import toolkit.database.expr.EQ;
import toolkit.database.fields.base.INT;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BOOL extends INT {
    public BOOL(String name) {
        super(name, true, 1, true, false);
    }

    public EQ eq(boolean value) {
        return new EQ(this, value ? 1 : 0);
    }

    public EQ eqTrue() {
        return eq(true);
    }

    public EQ eqFalse() {
        return eq(false);
    }

    public boolean getValue(ResultSet rs) {
        try {
            return rs.getInt(name) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
