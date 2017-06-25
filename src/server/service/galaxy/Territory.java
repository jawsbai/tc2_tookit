package server.service.galaxy;

import server.config.json.TerritoryJson;

public class Territory {
    public final Planet planet;
    public final int id;
    public final String key;
    private String name;
    private int factionId;

    public Territory(Planet planet, TerritoryJson config) {
        this.planet = planet;
        id = config.id;
        key = config.key;
    }

    public boolean isCapital() {
        return planet.terrIsCapital(key);
    }

    public void update() {
    }
}