package toolkit.database;

import toolkit.database.expr.*;
import toolkit.lang.Action1;
import toolkit.helper.AutoCloseableHelper;
import toolkit.print.Console;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Table {
    public final Database database;
    public final TableDefined tableDefined;

    public Table(Database database, TableDefined tableDefined) {
        this.database = database;
        this.tableDefined = tableDefined;
    }

    public final TableName getName() {
        return tableDefined.tableName;
    }

    public final void createTable() throws SQLException {
        Console.log("createTable `" + getName().name + "`");
        database.execute(tableDefined.dropSQL());
        database.execute(tableDefined.createSQL());
    }

    public final boolean insert(EQ... eqs) {
        try {
            return database.executeUpdate("@0", new INSERT(getName(), eqs)) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public final boolean update(EQ[] eqs, WHERE where) {
        try {
            return database.executeUpdate("@0 @1",
                    new UPDATE(getName(), eqs),
                    where) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public final boolean delete(WHERE where) {
        try {
            return database.executeUpdate("delete from @0 @1",
                    getName(), where) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists(WHERE where) {
        ResultSet rs = null;
        try {
            rs = database.executeQuery("select @0 from @1 @2",
                    tableDefined.primaryKey,
                    getName(),
                    where);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AutoCloseableHelper.close(rs);
        }
        return false;
    }

    public int count(WHERE where) {
        ResultSet rs = null;
        try {
            rs = database.executeQuery("select count(@0) from @1 @2",
                    tableDefined.primaryKey,
                    getName(),
                    where);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AutoCloseableHelper.close(rs);
        }
        return 0;
    }

    public void select(WHERE where, ORDER_BY orderBy, Action1<ResultSet> callback) {
        select(where, orderBy, Integer.MAX_VALUE, callback);
    }

    public void select(WHERE where, ORDER_BY orderBy, int maxRows, Action1<ResultSet> callback) {
        ResultSet rs = null;
        try {
            rs = database.executeQuery("select * from @0 @1 @2",
                    getName(),
                    where,
                    orderBy);

            int count = 0;
            while (rs.next()) {
                callback.invoke(rs);
                count++;
                if (count == maxRows) {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AutoCloseableHelper.close(rs);
        }
    }
}
