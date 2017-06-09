package com.tc2.server.real;

import com.tc2.server.config.json.TerritoryJson;

public class Territory {
    public final int id;
    public final String key;

    public Territory(TerritoryJson config) {
        id = config.id;
        key = config.key;
    }
}
