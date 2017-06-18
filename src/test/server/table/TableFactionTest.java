package test.server.table;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import rpc2j_table.Faction;
import rpc2j_table.Galaxy;
import server.table.TableFaction;
import server.table.TableGalaxy;
import test.TestEnv;
import toolkit.print.Console;

import java.util.Date;

public class TableFactionTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void test() throws Exception {
        TableFaction t = TestEnv.initDatabaseService().tableFaction;

        boolean f = t.createFaction(new Faction(1, "name1", "capital", new Date()));
        Assert.assertTrue(f);

        Console.log(JSON.toJSON(t.findFaction(1)));
    }


} 
