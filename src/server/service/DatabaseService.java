package server.service;

import toolkit.database.Database;
import toolkit.database.Table;
import server.config.json.DBConfigJson;
import server.table.*;
import toolkit.thread.ActiveObject;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseService extends ActiveObject {
    public final Database database;

    public final TableSeed tableSeed;
    public final TableGalaxy tableGalaxy;
    public final TablePlanet tablePlanet;
    public final TableTerritory tableTerritory;
    public final TableFaction tableFaction;
    public final TablePlayer tablePlayer;
    public final TableHero tableHero;
    public final TableUnit tableUnit;

    private ArrayList<Table> tables;

    public DatabaseService(DBConfigJson config) throws SQLException {
        super(1000 / 30);

        database = new Database(config.dbURL);

        tableSeed = new TableSeed(this, config.seedPrefix);
        tableGalaxy = new TableGalaxy(this);
        tablePlanet = new TablePlanet(this);
        tableTerritory = new TableTerritory(this);
        tableFaction = new TableFaction(this);
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
            if (field.getType().getSuperclass() == ServiceTable.class) {
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
