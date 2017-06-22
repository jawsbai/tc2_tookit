package server.service.galaxy;

import server.config.json.GalaxyJson;
import toolkit.thread.ActiveObject;

public class GalaxyService extends ActiveObject {
    public final int id;
    public final String key;
    private final Planet[] planets;

    public GalaxyService(GalaxyJson config) {
        super(1000 / 30);

        id = config.id;
        key = config.key;

        int len = config.planets.length;
        planets = new Planet[len];
        for (int i = 0; i < len; i++) {
            planets[i] = new Planet(this, config.planets[i]);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onTick(long et) {
        super.onTick(et);

        for (Planet planet : planets) {
            planet.update();
        }
    }
}
