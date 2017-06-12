package com.tc2.server.init;

import com.alibaba.fastjson.JSON;
import com.tc2.server.config.GalaxyConfig;
import com.tc2.server.config.StartupConfig;
import com.tc2.server.config.UserConfig;
import com.tc2.server.config.json.UserJson;
import com.tc2.server.galaxy.Galaxy;
import com.tc2.server.lang.Return;
import com.tc2.server.service.DatabaseService;
import com.tc2.server.service.AccountService;
import com.tc2.toolkit.print.Console;

public class Server {
    public final DatabaseService databaseService;
    public final AccountService accountService;

    public final Galaxy galaxy;

    public Server(String dataPath) throws Exception {
        StartupConfig startupConfig = new StartupConfig(dataPath);
        UserConfig userConfig = new UserConfig(dataPath);
        GalaxyConfig galaxyConfig = new GalaxyConfig(dataPath);

        databaseService = new DatabaseService(startupConfig.db);
        databaseService.start();

        accountService = new AccountService(userConfig);

        galaxy = new Galaxy(galaxyConfig);
        galaxy.start();

//        Console.log(JSON.toJSON(galaxy));
    }

    public void start() {
    }
}
