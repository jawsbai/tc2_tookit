package test.com.tc2.server.tables;

import com.tc2.server.config.StartupConfig;
import com.tc2.server.lang.Return;
import com.tc2.server.service.DatabaseService;
import com.tc2.server.tables.TableHero;
import com.tc2.toolkit.print.Console;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.TestEnv;

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
        StartupConfig startupConfig = new StartupConfig(TestEnv.getDataPath());
        DatabaseService databaseService = new DatabaseService(startupConfig.db);
        TableHero t = databaseService.tableHero;

        Return<String> rt = t.createHero("player1", "nickname1", 1, 2, 3, "at1",
                1, 2, 3, 4, 5);
        Assert.assertTrue(rt.isSuccess());

        rt = t.createHero("player1", "nickname1", 1, 2, 3, "at1",
                1, 2, 3, 4, 5);
        Assert.assertTrue(rt.isError());

        Assert.assertTrue(t.countByPlayerId("player1") == 1);

        rt = t.createHero("player1", "nickname2", 1, 2, 3, "at1",
                1, 2, 3, 4, 5);
        Assert.assertTrue(rt.isSuccess());

        Assert.assertTrue(t.countByPlayerId("player1") == 2);
    }


} 
