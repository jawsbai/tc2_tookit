package com.tc2.server.config;

import com.alibaba.fastjson.JSON;
import com.tc2.server.config.json.GalaxyJson;
import com.tc2.server.config.json.PlanetJson;
import com.tc2.toolkit.helper.FileHelper;
import com.tc2.toolkit.print.Console;

public class GalaxyConfig {
    public final int id;
    public final String key;
    public final PlanetJson[] planets;

    public GalaxyConfig(String folder) throws Exception {
        GalaxyJson json = FileHelper.readJson(folder + "/galaxy.json", GalaxyJson.class);
        if (json == null) {
            throw new Exception("init " + GalaxyConfig.class.getSimpleName() + " error");
        }

        id = json.id;
        key = json.key;
        planets = json.planets;

//        Console.log(JSON.toJSONString(this));
    }
}
