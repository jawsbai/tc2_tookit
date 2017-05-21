package com.tc2.database;

import com.tc2.database.expr.EQ;
import com.tc2.database.expr.INSERT;
import com.tc2.database.expr.FieldExpressions;
import com.tc2.toolkit.print.Console;

import java.sql.SQLException;

public class Table {
    public final Database database;
    public final TableDefined tableDefined;

    public Table(Database database, TableDefined tableDefined) {
        this.database = database;
        this.tableDefined = tableDefined;
    }

    public final void createTable() throws SQLException {
        Console.log("createTable `" + tableDefined.tableName + "`");
        database.execute(tableDefined.dropSQL());
        database.execute(tableDefined.createSQL());
    }

    public final boolean insert(FieldValue... values) {
        try {
            return database.executeUpdate("insert into @0@1",
                    tableDefined.tableName, new INSERT(values)) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public final boolean update(EQ... eqs) {
        try {
            return database.executeUpdate("update set @0 @1 where ",
                    tableDefined.tableName, new FieldExpressions(eqs)) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public boolean exists(String sql, Object... kvs) {
//        ResultSet rs = null;
//        try {
//            rs = db().executeQuery(sql, kvs);
//            return rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            AutoCloseableUtil.close(rs);
//        }
//        return false;
//    }
//
//    public int count(String sql, Object... kvs) {
//        ResultSet rs = null;
//        try {
//            rs = db().executeQuery(sql, kvs);
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            AutoCloseableUtil.close(rs);
//        }
//        return 0;
//    }
}
