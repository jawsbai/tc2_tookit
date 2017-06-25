package server.service.static_data;

import server.gencode.static_data.Map;
import toolkit.helper.FileHelper;

public class StaticDataService {
    public final String dataPath;

    public StaticDataService(String dataPath) {
        this.dataPath = dataPath;
    }

    public Map loadMap(String mapKey) {
        return FileHelper.readJson(Map.class, dataPath + "/maps/" + mapKey + ".json");
    }
}
