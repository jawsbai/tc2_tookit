package test;

import server.config.ConfigLoader;
import server.config.json.StartupJson;
import server.service.DatabaseService;

public class TestEnv {
    public static String getConfigPath() {
        return System.getProperty("user.dir") + "/config";
    }

    public static DatabaseService initDatabaseService() throws Exception {
        ConfigLoader configLoader = new ConfigLoader(TestEnv.getConfigPath());
        StartupJson startupJson = configLoader.load(StartupJson.class, "/startup_test.json");
        return new DatabaseService(startupJson.db);
    }
}
