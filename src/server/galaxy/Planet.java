package server.galaxy;

import server.config.json.PlanetJson;

public class Planet {
    public final int id;
    public final String key;
    public final Country[] countries;
    public final Territory[] territories;

    public Planet(PlanetJson config) {
        id = config.id;
        key = config.key;

        int len = config.countries.length;
        countries = new Country[len];
        for (int i = 0; i < len; i++) {
            countries[i] = new Country(config.countries[i]);
        }

        len = config.territories.length;
        territories = new Territory[len];
        for (int i = 0; i < len; i++) {
            territories[i] = new Territory(config.territories[i]);
        }
    }

    public void update() {
        for (Territory territory : territories) {
            territory.update();
        }
    }
}
