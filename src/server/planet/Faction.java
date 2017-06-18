package server.planet;

import server.config.json.FactionJson;

public class Faction {
    public final Planet planet;
    public final int id;
    public final String name;
    private String capital;

    public Faction(Planet planet, FactionJson config) {
        this.planet = planet;
        id = config.id;
        name = config.name;
        capital = config.capital;
    }

    public String getCapital() {
        return capital;
    }
}
