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

        RoomA r1 = new RoomA(s, 1000 * 3);
        r1.addToRoom();

        Promise<Boolean> p1;
        p1 = new RoomObjectA(r1, 0).addToRoomA();

        RoomObjectA ro1 = new RoomObjectA(r1, 1000);
        p1 = ro1.addToRoomA();
        p1.then(() -> {
            ro1.removeFromRoomA();
        });

        while (!r1.isRemoved()) {
            Thread.sleep(1);
        }

        p1 = new RoomObjectA(r1, 0).addToRoomA();
        TestHelper.waitPromise(p1);
        Assert.assertTrue(!p1.getResult());
    }

    @Test
    public void testTerritoryRoom() throws Exception {
        RoomService s = new RoomService();
        s.start();

        TerritoryRoom pr1 = new TerritoryRoom(s,
                1000 * 3,
                1,
                100);
        pr1.addToRoom();


        Promise<Return> promise;

        HeroA hero1 = new HeroA("hero1", 2);
        promise = new TerritoryRObject(pr1, 0, hero1).addToTerritoryRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isError());

        hero1 = new HeroA("hero1", 1);
        promise = new TerritoryRObject(pr1, 0, hero1).addToTerritoryRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isSuccess());

        hero1 = new HeroA("hero1", 1);
        promise = new TerritoryRObject(pr1, 0, hero1).addToTerritoryRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isError());

        while (pr1.getFactionLockTime() > 0) {
            Thread.sleep(1);
        }

        hero1 = new HeroA("hero2", 2);
        promise = new TerritoryRObject(pr1, 0, hero1).addToTerritoryRoom();
        TestHelper.waitPromise(promise);
        Assert.assertTrue(promise.getResult().isSuccess());

        while (!pr1.isRemoved()) {
            Thread.sleep(1);
        }
    }

} 
