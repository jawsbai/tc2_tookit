package test.server.table;

import com.alibaba.fastjson.JSON;
import rpc2j_table.Hero;
import toolkit.lang.Return;
import server.service.database.table.TableHero;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.TestHelper;
import toolkit.print.Console;

import java.util.Date;


/**
 * TableHero Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 12, 2017</pre>
 */
public class TableHeroTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testCreateHero() throws Exception {
        TableHero t = TestHelper.initDatabaseService().tableHero;

        Hero hero = new Hero(
                null,
                "player1",
                "nickname1",
                new Date(),
                false,
                1,
                1,
                "",
                "",
                1,
                2,
                3,
                4,
                5
        );

        Return<String> rt = t.createHero(hero);
        Assert.assertTrue(rt.isSuccess());

        Console.log(JSON.toJSON(t.findHero("solarHero1")));

        rt = t.createHero(hero);
        Assert.assertTrue(rt.isError());

        Assert.assertTrue(t.countByPlayerId("player1") == 1);

        hero.setNickname("nickname2");
        rt = t.createHero(hero);
        Assert.assertTrue(rt.isSuccess());

        Assert.assertTrue(t.countByPlayerId("player1") == 2);

        Console.log(JSON.toJSON(t.findHerosByPlayerId("player1")));
    }


}
