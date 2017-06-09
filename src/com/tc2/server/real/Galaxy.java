package com.tc2.server.real;

import com.tc2.server.config.GalaxyConfig;

public class Galaxy {
    public final int id;
    public final String key;
    public final Planet[] planets;

    public Galaxy(GalaxyConfig config) {
        id = config.id;
        key = config.key;

        int len = config.planets.length;
        planets = new Planet[len];
        for (int i = 0; i < len; i++) {
            planets[i] = new Planet(config.planets[i]);
        }
    }
}

//A1 B1 C1
//A2 B2 C2
//A3 B3 C3