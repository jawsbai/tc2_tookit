package com.tc2.server.config;

import com.alibaba.fastjson.JSON;
import com.tc2.server.config.json.StartupJson;
import com.tc2.toolkit.helper.FileHelper;
import com.tc2.toolkit.print.Console;

public class StartupConfig {
    public final String dbURL;

    public StartupConfig(String folder) throws Exception {
        StartupJson json = FileHelper.readJson(folder + "/startup.json", StartupJson.class);
        if (json == null) {
            throw new Exception("init " + StartupConfig.class.getSimpleName() + " error");
        }

        dbURL = json.dbURL;

//        Console.log(JSON.toJSONString(this));
    }
}
