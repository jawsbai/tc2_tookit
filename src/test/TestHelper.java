package test;

import server.config.ConfigLoader;
import server.config.json.StartupJson;
import server.service.database.DatabaseService;
import toolkit.promise.Promise;

public class TestHelper {
    public static String getConfigPath() {
        return System.getProperty("user.dir") + "/config";
    }

    public static DatabaseService initDatabaseService() throws Exception {
        ConfigLoader configLoader = new ConfigLoader(TestHelper.getConfigPath());
        StartupJson startupJson = configLoader.load(StartupJson.class, "/startup_test.json");
        return new DatabaseService(startupJson.db);
    }

    public static void waitPromise(Promise promise) {
        while (promise.getState() == Promise.PENDING) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
