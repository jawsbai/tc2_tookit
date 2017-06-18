package test.server.service;

import org.junit.After;
import org.junit.Before;
import server.room.Guest;
import server.room.Room;
import server.room.WarRoom;
import server.service.RoomService;
import toolkit.print.Console;
import toolkit.thread.ActiveObject;

import java.math.RoundingMode;

public class RoomServiceTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @org.junit.Test
    public void test() throws Exception {
//        ConfigLoader configLoader = new ConfigLoader(TestEnv.getConfigPath());
//        GalaxyJson galaxyJson = configLoader.load(GalaxyJson.class, "/galaxy.json");

        RoomService s = new RoomService();
        s.start();
//        s.start().then(() -> {
//
//        });

        Room r1 = new Room(s, 1000 * 3);
        r1.addToService().then(() -> {
//            Console.log("fin");
        });

        new Guest(r1, 0).addToRoom();
        new Guest(r1, 10000).addToRoom();

        while (!r1.isRemoved()) {
            Thread.sleep(1);
        }

        WarRoom wr1 = new WarRoom(s, 1000 * 3, 1);
        wr1.addToService();

        while (!wr1.isRemoved()) {
            Thread.sleep(1);
        }
    }

} 
