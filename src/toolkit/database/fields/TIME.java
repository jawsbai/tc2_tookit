package toolkit.database.fields;

import toolkit.database.expr.EQ;
import toolkit.database.fields.base.VARCHAR;
import toolkit.print.Console;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TIME extends VARCHAR {
    public TIME(String name) {
        super(name, true, 50);
    }

    public EQ eq(Date value) {
        return new EQ(this, String.valueOf(value.getTime()));
    }

    public EQ eqNow() {
        return eq(new Date());
    }

    public Date getValue(ResultSet rs) {
        try {
            return new Date(Long.parseLong(rs.getString(name)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
