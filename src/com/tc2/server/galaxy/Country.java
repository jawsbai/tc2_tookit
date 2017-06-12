package com.tc2.server.galaxy;

import com.tc2.server.config.json.CountryJson;

public class Country {
    public final int id;
    public final String name;
    public final String capital;

    public Country(CountryJson config) {
        id = config.id;
        name = config.name;
        capital = config.capital;
    }
}
