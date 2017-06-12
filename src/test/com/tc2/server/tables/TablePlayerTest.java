package test.com.tc2.server.tables;

import com.tc2.server.config.StartupConfig;
import com.tc2.server.service.DatabaseService;
import com.tc2.server.tables.TablePlayer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.TestEnv;

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
        StartupConfig startupConfig = new StartupConfig(TestEnv.getDataPath());
        DatabaseService databaseService = new DatabaseService(startupConfig.db);
        TablePlayer t = databaseService.tablePlayer;

        Assert.assertTrue(!t.userExists("user1"));

        Assert.assertTrue(t.createPlayer("user1").isSuccess());
        Assert.assertTrue(t.createPlayer("user1").isError());

        Assert.assertTrue(t.userExists("user1"));

        Assert.assertTrue(t.createPlayer("user2").isSuccess());
    }


} 
