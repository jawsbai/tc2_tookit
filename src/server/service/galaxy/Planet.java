package server.service.galaxy;

import server.config.json.PlanetJson;

import java.util.Objects;

public class Planet {
    public final GalaxyService service;
    public final int id;
    public final String key;
    private String name;
    private final Faction[] factions;
    private final Territory[] territories;

    public Planet(GalaxyService service, PlanetJson config) {
        this.service = service;
        id = config.id;
        key = config.key;
        name = config.name;

        int len = config.factions.length;
        factions = new Faction[len];
        for (int i = 0; i < len; i++) {
            factions[i] = new Faction(this, config.factions[i]);
        }

        len = config.territories.length;
        territories = new Territory[len];
        for (int i = 0; i < len; i++) {
            territories[i] = new Territory(this, config.territories[i]);
        }
    }

    public boolean terrIsCapital(String terrKey) {
        for (Faction country : factions) {
            if (Objects.equals(country.getCapital(), terrKey)) {
                return true;
            }
        }
        return false;
    }

    public Territory getTerrByKey(String terrKey) {
        for (Territory territory : territories) {
            if (Objects.equals(territory.key, terrKey)) {
                return territory;
            }
        }
        return null;
    }

    public void update() {
        for (Territory territory : territories) {
            territory.update();
        }
    }
}
