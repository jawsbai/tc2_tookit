package server.config;

import server.config.json.DBConfigJson;
import server.config.json.StartupJson;
import toolkit.helper.FileHelper;

public class StartupConfig {
    public final DBConfigJson db;
    public final int maxHeros = 3;

    public StartupConfig(String folder) throws Exception {
        StartupJson json = FileHelper.readJson(folder + "/startup.json", StartupJson.class);
        if (json == null) {
            throw new Exception("init " + StartupConfig.class.getSimpleName() + " error");
        }

        db = json.db;

//        Console.log(JSON.toJSONString(this));
    }
}
