package toolkit.database.fields;

import toolkit.database.expr.EQ;
import toolkit.database.fields.base.VARCHAR;

import java.sql.ResultSet;
import java.sql.SQLException;

public class STRING extends VARCHAR {
    public STRING(String name, int len) {
        super(name, true, len);
    }

    public EQ eq(String value) {
        return new EQ(this, value);
    }

    public String getValue(ResultSet rs) {
        try {
            return rs.getString(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
