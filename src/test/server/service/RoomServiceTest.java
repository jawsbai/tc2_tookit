package test.server.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import server.hero.IHero;
import server.service.room.*;
import test.TestHelper;
import toolkit.helper.TimeHelper;
import toolkit.lang.Return;
import toolkit.print.Console;
import toolkit.promise.Promise;

/**
 * RoomService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 22, 2017</pre>
 */
public class RoomServiceTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    class RoomA extends Room<RoomObject> {
        public RoomA(RoomService service, int maxTime) {
            super(service, maxTime);
        }
    }

    class RoomObjectA extends RoomObject<RoomA> {
        public RoomObjectA(RoomA space, int queueTime) {
            super(space, queueTime);
        }

        public Promise<Boolean> addToRoomA() {
            return addToRoom();
        }

        public Promise<Boolean> removeFromRoomA() {
            return removeFromRoom();
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

    @Test
    public void testRoom() throws Exception {
        RoomService s = new RoomService();
        s.start();

        RoomA room = new RoomA(s, 1000 * 3);
        room.addToRoom();

        Promise<Boolean> p1;
        p1 = new RoomObjectA(room, 0).addToRoomA();

        RoomObjectA ro1 = new RoomObjectA(room, 1000);
        p1 = ro1.addToRoomA();
        p1.then(() -> {
            ro1.removeFromRoomA();
        });

        while (!room.isRemoved()) {
            Thread.sleep(1);
        }

        p1 = new RoomObjectA(room, 0).addToRoomA();
        TestHelper.waitPromise(p1);
        Assert.assertTrue(!p1.getResult());
    }

    @Test
    public void testTerritoryRoom() throws Exception {
        RoomService s = new RoomService();
        s.start();

        TerritoryRoom room = new TerritoryRoom(s,
                1000 * 3,
                1,
                100);
        room.addToRoom();


        Promise<Return> promise;

        HeroA hero1 = new HeroA("hero1", 2);
        promise = new TerritoryRObject(room, 0, hero1).addToTerritoryRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isError());

        hero1 = new HeroA("hero1", 1);
        promise = new TerritoryRObject(room, 0, hero1).addToTerritoryRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isSuccess());

        hero1 = new HeroA("hero1", 1);
        promise = new TerritoryRObject(room, 0, hero1).addToTerritoryRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isError());

        while (room.getFactionLockTime() > 0) {
            Thread.sleep(1);
        }

        hero1 = new HeroA("hero2", 2);
        promise = new TerritoryRObject(room, 0, hero1).addToTerritoryRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isSuccess());

        while (!room.isRemoved()) {
            Thread.sleep(1);
        }
    }

    @Test
    public void testWarRoom() throws Exception {
        RoomService s = new RoomService();
        s.start();

        WarRoom room = new WarRoom(s,
                1000 * 3,
                1000,
                1,
                2);
        room.addToRoom();

        Promise<Return> promise;

        HeroA hero1 = new HeroA("hero1", 1);
        promise = new WarRObject(room, 0, hero1).addToWarRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isSuccess());

        hero1 = new HeroA("hero1", 1);
        promise = new WarRObject(room, 0, hero1).addToWarRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isError());

        HeroA hero2 = new HeroA("hero2", 1);
        promise = new WarRObject(room, 0, hero2).addToWarRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isSuccess());

        HeroA hero3 = new HeroA("hero3", 1);
        promise = new WarRObject(room, 0, hero3).addToWarRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isError());

        Thread.sleep(2000);

        Console.log(room.isEndBuffer(), room.elapsedTime());

        HeroA hero4 = new HeroA("hero4", 1);
        promise = new WarRObject(room, 0, hero4).addToWarRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isError());


        while (!room.isRemoved()) {
            Thread.sleep(1);
        }
    }

} 
