package server.init;

import server.service.AccountService;
import server.service.DatabaseService;
import server.service.GalaxyService;
import server.service.PlayerService;

public class Locator {
    private static DatabaseService databaseService;
    private static GalaxyService galaxyService;
    private static AccountService accountService;
    private static PlayerService playerService;

    public static void register(DatabaseService service) {
        databaseService = service;
    }

    public static void register(GalaxyService service) {
        galaxyService = service;
    }

    public static void register(AccountService service) {
        accountService = service;
    }

    public static void register(PlayerService service) {
        playerService = service;
    }

    public static DatabaseService databaseService() {
        return databaseService;
    }

    public static GalaxyService galaxyService() {
        return galaxyService;
    }

    public static AccountService accountService() {
        return accountService;
    }

    public static PlayerService playerService() {
        return playerService;
    }
}
