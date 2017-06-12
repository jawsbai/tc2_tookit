package com.tc2.server.config;

import com.alibaba.fastjson.JSON;
import com.tc2.server.config.json.DBConfig;
import com.tc2.server.config.json.StartupJson;
import com.tc2.toolkit.helper.FileHelper;
import com.tc2.toolkit.print.Console;

public class StartupConfig {
    public final DBConfig db;
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
