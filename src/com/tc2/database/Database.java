package com.tc2.database;

import com.tc2.database.expr.Expression;
import com.tc2.toolkit.utils.AutoCloseableHelper;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {
    private Connection _conn;
    private Statement _stmt;

    public Database() {
    }

    public void connect(String connStr) throws SQLException {
        close();

        _conn = DriverManager.getConnection(connStr);
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
//
//    public boolean exists(String sql, Object... args) {
//        boolean find = false;
//        ResultSet rs = null;
//        try {
//            rs = executeQuery(sql, args);
//            find = rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return find;
//    }
//
//    public int count(String sql, Object... args) {
//        int count = 0;
//        ResultSet rs = null;
//        try {
//            rs = executeQuery(sql, args);
//            if (rs.next()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return count;
//    }
//
//    public <T> T getJson(Class<T> t, String sql, Object... args) {
//        T find = null;
//        ResultSet rs = null;
//        try {
//            rs = executeQuery(sql, args);
//            if (rs.next()) {
//                find = JSONUtil.parse(t, rs.getString("json"));
//
//                if (find != null) {
//                    try {
//                        Field id = find.getClass().getField("id");
//                        id.set(find, rs.getInt("id"));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return find;
//    }
//
//    public <T> ArrayList<T> getJsonArray(Class<T> t, String sql, Object... args) {
//        ArrayList<T> list = new ArrayList<>();
//        ResultSet rs = null;
//        try {
//            rs = executeQuery(sql, args);
//            while (rs.next()) {
//                T find = JSONUtil.parse(t, rs.getString("json"));
//                if (find == null) {
//                    Console.log(sql, "getJsonArray find=null");
//                } else {
//                    try {
//                        Field id = find.getClass().getField("id");
//                        id.set(find, rs.getInt("id"));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                list.add(find);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
}
