package com.tc2.database;

import com.tc2.database.expr.*;
import com.tc2.toolkit.action.Action1;
import com.tc2.toolkit.print.Console;
import com.tc2.toolkit.helper.AutoCloseableHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {
    public final Database database;
    public final TableDefined tableDefined;

    public Table(Database database, TableDefined tableDefined) {
        this.database = database;
        this.tableDefined = tableDefined;
    }

    public final TableName tableName() {
        return tableDefined.tableName;
    }

    public final void createTable() throws SQLException {
        Console.log("createTable `" + tableName().name + "`");
        database.execute(tableDefined.dropSQL());
        database.execute(tableDefined.createSQL());
    }

    public final boolean insert(EQ... eqs) {
        try {
            return database.executeUpdate("@0", new INSERT(tableName(), eqs)) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public final boolean update(EQ[] eqs, WHERE where) {
        try {
            return database.executeUpdate("@0 @1",
                    new UPDATE(tableName(), eqs),
                    where) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public final boolean delete(WHERE where) {
        try {
            return database.executeUpdate("delete from @0 @1",
                    tableName(), where) == 1;
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
                    tableName(),
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
                    tableName(),
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

    public void select(WHERE where, ORDER_BY orderBy, int maxRows, Action1<ResultSet> callback) {
        ResultSet rs = null;
        try {
            rs = database.executeQuery("select * from @0 @1 @2",
                    tableName(),
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
