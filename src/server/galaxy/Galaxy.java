package server.galaxy;

import server.config.GalaxyConfig;
import toolkit.thread.ActiveObject;

public class Galaxy extends ActiveObject {
    public final int id;
    public final String key;
    public final Planet[] planets;

    public Galaxy(GalaxyConfig config) {
        super(1000 / 30);

        id = config.id;
        key = config.key;

        int len = config.planets.length;
        planets = new Planet[len];
        for (int i = 0; i < len; i++) {
            planets[i] = new Planet(config.planets[i]);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected synchronized void onTick() {
        super.onTick();

        for (Planet planet : planets) {
            planet.update();
        }
    }
}