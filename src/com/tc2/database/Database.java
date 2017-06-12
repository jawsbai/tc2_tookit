package com.tc2.database;

import com.tc2.database.expr.Expression;
import com.tc2.toolkit.helper.AutoCloseableHelper;
import com.tc2.toolkit.print.Console;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {
    private Connection _conn;
    private Statement _stmt;

    public Database(String dbURL) throws SQLException {
        _conn = DriverManager.getConnection(dbURL);
        _stmt = _conn.createStatement();
    }

    public void close() {
        AutoCloseableHelper.close(_stmt);
        AutoCloseableHelper.close(_conn);
    }

    public static String convertValue(Object value) {
        if (value instanceof String) {
            return "'" + value.toString()
                    .replace("'", "\\\\'")
                    .replace("\r", "\\\\r")
                    .replace("\n", "\\\\n") + "'";
        }
        return value.toString();
    }

    private String formatValue(Object value) {
        if (value instanceof TableName) {
            return "`" + ((TableName) value).name + "`";
        } else if (value instanceof FieldDefined) {
            return "`" + ((FieldDefined) value).name + "`";
        } else if (value instanceof Expression) {
            return ((Expression) value).toSQL();
        }
        return convertValue(value);
    }

    public String formatSQL(String sql, Object... values) {
        Pattern p = Pattern.compile("@(\\d{1,})", Pattern.MULTILINE);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            int index = Integer.parseInt(m.group(1));
            if (values.length > index) {
                m.appendReplacement(sb, formatValue(values[index]));
            } else {
                m.appendReplacement(sb, m.group());
            }
        }
        return sb.length() == 0 ? sql : sb.toString();
    }

    public boolean execute(String sql, Object... values) throws SQLException {
        return _stmt.execute(formatSQL(sql, values));
    }

    public int executeUpdate(String sql, Object... values) throws SQLException {
        return _stmt.executeUpdate(formatSQL(sql, values));
    }

    public ResultSet executeQuery(String sql, Object... values) throws SQLException {
        return _stmt.executeQuery(formatSQL(sql, values));
    }
}
