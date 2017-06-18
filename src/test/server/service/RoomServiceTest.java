package test.server.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import server.hero.IHero;
import server.room.Guest;
import server.room.Room;
import server.room.WarGuest;
import server.room.WarRoom;
import server.service.RoomService;
import test.TestHelper;
import toolkit.lang.Return;
import toolkit.print.Console;
import toolkit.promise.Promise;

public class RoomServiceTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    class RoomA extends Room {
        public RoomA(RoomService service, int maxTime) {
            super(service, maxTime);
        }
    }

    class GuestA extends Guest {
        public GuestA(Room room, int queueTime) {
            super(room, queueTime);
        }

        public Promise<Boolean> addToRoomA() {
            return addToRoom();
        }
    }

    class HeroA implements IHero {
        public String heroId;
        public int factionId;

        public HeroA(String heroId, int factionId) {
            this.heroId = heroId;
            this.factionId = factionId;
        }

        @Override
        public String getHeroId() {
            return heroId;
        }

        @Override
        public int getFactionId() {
            return factionId;
        }
    }

    @org.junit.Test
    public void test() throws Exception {
//        ConfigLoader configLoader = new ConfigLoader(TestEnv.getConfigPath());
//        GalaxyJson galaxyJson = configLoader.load(GalaxyJson.class, "/galaxy.json");

        RoomService s = new RoomService();
        s.start();

//        RoomA r1 = new RoomA(s, 1000 * 3);
//        r1.addToService();
//
//        new GuestA(r1, 0).addToRoomA();
//        new GuestA(r1, 10000).addToRoomA();
//
//        while (!r1.isRemoved()) {
//            Thread.sleep(1);
//        }
//
//        Console.log("-------------------");

        WarRoom wr1 = new WarRoom(s, 1000 * 3, 2);
        wr1.addToService();

        HeroA hero1 = new HeroA("hero1", 1);
        new WarGuest(wr1, 0, hero1).addToWarRoom();

        Promise<Return<String>> rt1 = new WarGuest(wr1, 0, hero1).addToWarRoom();
        TestHelper.waitPromise(rt1);
        Assert.assertTrue(rt1.result().isError());

        HeroA hero2 = new HeroA("hero2", 1);
        new WarGuest(wr1, 0, hero2).addToWarRoom();

        HeroA hero3 = new HeroA("hero3", 1);
        Promise<Return<String>> rt2 = new WarGuest(wr1, 0, hero3).addToWarRoom();
        TestHelper.waitPromise(rt2);
        Assert.assertTrue(rt2.result().isError());

        while (!wr1.isRemoved()) {
            Thread.sleep(1);
        }
    }

} 
