package test.server.tables;

import com.alibaba.fastjson.JSON;
import rpc2j_table.Unit;
import server.config.StartupConfig;
import server.service.DatabaseService;
import server.tables.TableUnit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.TestEnv;
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
        StartupConfig startupConfig = new StartupConfig(TestEnv.getDataPath());
        DatabaseService databaseService = new DatabaseService(startupConfig.db);
        TableUnit t = databaseService.tableUnit;

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
