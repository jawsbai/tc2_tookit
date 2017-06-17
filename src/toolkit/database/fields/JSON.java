package toolkit.database.fields;

import toolkit.database.expr.EQ;
import toolkit.database.fields.base.TEXT;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JSON<T> extends TEXT {
    public JSON(String name) {
        super(name, true);
    }

    public EQ eq(T value) {
        return new EQ(this, com.alibaba.fastjson.JSON.toJSONString(value));
    }

    public T getValue(ResultSet rs) {
//        try {
////            return rs.getString(name);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
