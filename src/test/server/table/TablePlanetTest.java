package test.server.table;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import rpc2j_table.Planet;
import server.table.TablePlanet;
import test.TestHelper;
import toolkit.print.Console;

import java.util.Date;

/**
 * TablePlanet Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 18, 2017</pre>
 */
public class TablePlanetTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void test() throws Exception {
        TablePlanet t = TestHelper.initDatabaseService().tablePlanet;

        boolean f = t.createPlanet(new Planet(1, "name1", new Date()));
        Assert.assertTrue(f);

        Console.log(JSON.toJSON(t.findPlanet(1)));
    }


} 
