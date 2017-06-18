package test.server.table;

import com.alibaba.fastjson.JSON;
import server.service.DatabaseService;
import server.table.TablePlayer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.TestEnv;
import toolkit.print.Console;

/**
 * TablePlayer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 12, 2017</pre>
 */
public class TablePlayerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testCreatePlayer() throws Exception {
        TablePlayer t = TestEnv.initDatabaseService().tablePlayer;

        Assert.assertTrue(!t.userExists("user1"));

        Assert.assertTrue(t.createPlayer("user1").isSuccess());
        Assert.assertTrue(t.createPlayer("user1").isError());

        Console.log(JSON.toJSON(t.findPlayerByUserId("user1")));

        Assert.assertTrue(t.userExists("user1"));

        Assert.assertTrue(t.createPlayer("user2").isSuccess());
    }


}
