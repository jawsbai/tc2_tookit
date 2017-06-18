package toolkit.database.fields;

import toolkit.database.expr.EQ;
import toolkit.database.fields.base.INT;

import java.sql.ResultSet;
import java.sql.SQLException;

public class INTEGER_AUTO_INCREMENT extends INT {
    public INTEGER_AUTO_INCREMENT(String name) {
        super(name, true, 11, false, true);
    }

    public EQ eq(int value) {
        return new EQ(this, value);
    }

    public int getValue(ResultSet rs) {
        try {
            return rs.getInt(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
