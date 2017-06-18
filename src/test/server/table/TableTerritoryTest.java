package test.server.table;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import rpc2j_table.Galaxy;
import rpc2j_table.Territory;
import server.table.TableGalaxy;
import server.table.TableTerritory;
import test.TestEnv;
import toolkit.print.Console;

import java.util.Date;

/**
 * TableTerritory Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 18, 2017</pre>
 */
public class TableTerritoryTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void test() throws Exception {
        TableTerritory t = TestEnv.initDatabaseService().tableTerritory;

        boolean f = t.createTerritory(new Territory(1, "name1", 1, new Date()));
        Assert.assertTrue(f);

        Console.log(JSON.toJSON(t.findTerritory(1)));
    }


} 
