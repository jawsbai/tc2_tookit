package test;

import com.tc2.server.config.StartupConfig;
import com.tc2.server.service.DatabaseService;

public class TestEnv {
    public static String getDataPath() {
        return System.getProperty("user.dir") + "/data";
    }
}
