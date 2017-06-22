package test.server.table;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import rpc2j_table.Galaxy;
import server.service.database.table.TableGalaxy;
import test.TestHelper;
import toolkit.print.Console;

import java.util.Date;

public class TableGalaxyTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void test() throws Exception {
        TableGalaxy t = TestHelper.initDatabaseService().tableGalaxy;

        boolean f = t.createGalaxy(new Galaxy(1, new Date()));
        Assert.assertTrue(f);

        Console.log(JSON.toJSON(t.findGalaxy(1)));
    }
} 
