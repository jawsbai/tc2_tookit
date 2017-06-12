package test.com.tc2.server.tables;

import com.tc2.server.config.StartupConfig;
import com.tc2.server.service.DatabaseService;
import com.tc2.server.tables.TableUnit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.TestEnv;

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

        boolean f = t.createUnit("hero1", "name1", "data1",
                new String[]{"1"}, 1, 100, 1000, 0);
        Assert.assertTrue(f);
    }


} 
