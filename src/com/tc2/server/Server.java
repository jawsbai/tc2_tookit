package com.tc2.server;

import com.alibaba.fastjson.JSON;
import com.tc2.server.config.GalaxyConfig;
import com.tc2.server.config.StartupConfig;
import com.tc2.server.real.Galaxy;
import com.tc2.toolkit.print.Console;

public class Server {
    private final StartupConfig startupConfig;
    private final Galaxy galaxy;

    public Server(String startupPath) throws Exception {
        startupConfig = new StartupConfig(startupPath);
        GalaxyConfig galaxyConfig = new GalaxyConfig(startupPath);

        galaxy = new Galaxy(galaxyConfig);

        Console.log(JSON.toJSON(galaxy));
    }

    public void start() {
    }
}
