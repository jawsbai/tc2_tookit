package test.server.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.hero.IHero;
import server.service.RoomService;
import server.room.RoomObject;
import server.room.Room;
import test.TestHelper;
import toolkit.promise.Promise;

/**
 * SpaceService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 19, 2017</pre>
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

    class LodgerA extends RoomObject<RoomA> {
        public LodgerA(RoomA space, int queueTime) {
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
            return null;
        }

        @Override
        public int getFactionId() {
            return 0;
        }
    }

    @Test
    public void testRoom() throws Exception {
        RoomService s = new RoomService();
        s.start();

        RoomA r1 = new RoomA(s, 1000 * 3);
        r1.addToRoom();

        Promise<Boolean> p1;
        p1 = new LodgerA(r1, 0).addToRoomA();

        LodgerA g1 = new LodgerA(r1, 1000);
        p1 = g1.addToRoomA();
        p1.then(() -> {
            g1.removeFromRoomA();
        });

        while (!r1.isRemoved()) {
            Thread.sleep(1);
        }

        p1 = new LodgerA(r1, 0).addToRoomA();
        TestHelper.waitPromise(p1);
        Assert.assertTrue(!p1.getResult());
    }

//    @Test
//    public void testWarSpace() throws Exception {
//RoomService s = new RoomService();
//        s.start();
//
//    WarRoom wr1 = new WarRoom(s, 1000 * 3, 2);
//        wr1.addToRoom();
//
//    RoomServiceTest.HeroA hero1 = new RoomServiceTest.HeroA("hero1", 1);
//        new WarGuest(wr1, 0, hero1).addToWarRoom();
//
//    Promise<Return<String>> rt1 = new WarGuest(wr1, 0, hero1).addToWarRoom();
//        TestHelper.waitPromise(rt1);
//        Assert.assertTrue(rt1.getResult().isError());
//
//    RoomServiceTest.HeroA hero2 = new RoomServiceTest.HeroA("hero2", 1);
//        new WarGuest(wr1, 0, hero2).addToWarRoom();
//
//    RoomServiceTest.HeroA hero3 = new RoomServiceTest.HeroA("hero3", 1);
//    Promise<Return<String>> rt2 = new WarGuest(wr1, 0, hero3).addToWarRoom();
//        TestHelper.waitPromise(rt2);
//        Assert.assertTrue(rt2.getResult().isError());
//
//    RoomServiceTest.HeroA hero4 = new RoomServiceTest.HeroA("hero4", 2);
//    WarGuest warGuest = new WarGuest(wr1, 100, hero4);
//        warGuest.addToWarRoom().then(() -> {
//        warGuest.removeFromWarRoom();
//    });
//
//        while (!wr1.isRemoved()) {
//        Thread.sleep(1);
//    }
//    }

} 
