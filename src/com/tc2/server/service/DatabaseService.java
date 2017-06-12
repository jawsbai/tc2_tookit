package com.tc2.server.service;

import com.tc2.database.Database;
import com.tc2.database.Table;
import com.tc2.server.config.json.DBConfig;
import com.tc2.server.tables.*;
import com.tc2.toolkit.thread.ActiveObject;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseService extends ActiveObject {
    public final Database database;

    public final TableSeed tableSeed;
    public final TablePlayer tablePlayer;
    public final TableHero tableHero;
    public final TableUnit tableUnit;

    private ArrayList<Table> tables;

    public DatabaseService(DBConfig config) throws SQLException {
        super(1000 / 30);

        database = new Database(config.dbURL);

        tableSeed = new TableSeed(this, config.seedPrefix);
        tablePlayer = new TablePlayer(this);
        tableHero = new TableHero(this);
        tableUnit = new TableUnit(this);

        tables = getTablesFromClass();

        if (config.createTables) {
            createTables();
        }
    }

    public String newId(String key) {
        return tableSeed.newId(key);
    }

    private ArrayList<Table> getTablesFromClass() {
        ArrayList<Table> tables = new ArrayList<>();
        for (Field field : getClass().getFields()) {
            if (field.getType().getSuperclass() == BaseTable.class) {
                try {
                    Table table = (Table) field.get(this);
                    tables.add(table);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return tables;
    }

    private void createTables() throws SQLException {
        for (Table table : tables) {
            table.createTable();
        }
    }
}
