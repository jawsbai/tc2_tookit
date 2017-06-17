package server.galaxy;

import server.config.json.TerritoryJson;


public class Territory {
    public final int id;
    public final String key;

    public Territory(TerritoryJson config) {
        id = config.id;
        key = config.key;
    }

    public void update() {
    }
}

//A1 B1 C1
//A2 B2 C2
//A3 B3 C3