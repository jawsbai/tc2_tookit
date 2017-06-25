package test.server.table;

import com.alibaba.fastjson.JSON;
import server.gencode.table.Unit;
import server.service.database.table.TableUnit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.TestHelper;
import toolkit.print.Console;

import java.util.Date;

/**
 * TableUnit Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 12, 2017</pre>
 */
public class TableUnitTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testCreateUnit() throws Exception {
        TableUnit t = TestHelper.initDatabaseService().tableUnit;

        Unit unit = new Unit(
                null,
                "hero1",
                "11111",
                new Date(),
                "1",
                "222",
                1,
                2,
                3,
                4);

        boolean f = t.createUnit(unit);
        Assert.assertTrue(f);

        Console.log(JSON.toJSON(t.findUnitsByHeroId("hero1")));
    }


}
