package test.server.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.hero.IHero;
import server.service.SpaceService;
import server.space.SpatialObject;
import server.space.Space;
import test.TestHelper;
import toolkit.print.Console;
import toolkit.promise.Promise;

/**
 * SpaceService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 19, 2017</pre>
 */
public class SpaceServiceTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    class SpaceA extends Space {
        public SpaceA(SpaceService service, int maxTime) {
            super(service, maxTime);
        }
    }

    class SpaceObjectA extends SpatialObject<SpaceA> {
        public SpaceObjectA(SpaceA space, int queueTime) {
            super(space, queueTime);
        }

        public Promise<Boolean> addToSpaceA() {
            return addToSpace();
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
    public void testSpace() throws Exception {
        SpaceService s = new SpaceService();
        s.start();

        SpaceA s1 = new SpaceA(s, 1000 * 3);
        s1.addToService();

        Promise<Boolean> p1;
        p1 = new SpaceObjectA(s1, 0).addToSpaceA();
        p1 = new SpaceObjectA(s1, 0).addToSpaceA();

        while (!s1.isRemoved()) {
            Thread.sleep(1);
        }

        p1 = new SpaceObjectA(s1, 0).addToSpaceA();
        TestHelper.waitPromise(p1);
        Assert.assertTrue(!p1.getResult());
    }

//    @Test
//    public void testWarSpace() throws Exception {
//RoomService s = new RoomService();
//        s.start();
//
//    WarRoom wr1 = new WarRoom(s, 1000 * 3, 2);
//        wr1.addToService();
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
