package server.config;

import server.config.json.GalaxyJson;
import server.config.json.PlanetJson;
import toolkit.helper.FileHelper;

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
