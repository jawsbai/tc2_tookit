package server.init;

import server.config.ConfigLoader;
import server.config.json.AccountJson;
import server.config.json.GalaxyJson;
import server.config.json.StartupJson;
import server.service.account.AccountService;
import server.service.database.DatabaseService;
import server.service.galaxy.GalaxyService;
import server.service.player.PlayerService;

public class Server {
    public Server(String configPath) throws Exception {
        ConfigLoader configLoader = new ConfigLoader(configPath);

        StartupJson startupJson = configLoader.load(StartupJson.class, "/startup.json");

        //DatabaseService
        DatabaseService databaseService = new DatabaseService(startupJson.db);
        databaseService.start();
        Locator.register(databaseService);

        //GalaxyService
        GalaxyJson galaxyJson = configLoader.load(GalaxyJson.class, "/galaxy.json");
        GalaxyService galaxyService = new GalaxyService(galaxyJson);
        Locator.register(galaxyService);

        //AccountService
        AccountJson accountJson = configLoader.load(AccountJson.class, "/account.json");
        AccountService accountService = new AccountService(accountJson);
        Locator.register(accountService);

        //PlayerService
        PlayerService playerService = new PlayerService();
        playerService.start();
        Locator.register(playerService);
    }

    public void start() {
    }
}
