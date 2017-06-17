package server.init;

import server.config.GalaxyConfig;
import server.config.StartupConfig;
import server.config.UserConfig;
import server.galaxy.Galaxy;
import server.service.DatabaseService;
import server.service.AccountService;

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
