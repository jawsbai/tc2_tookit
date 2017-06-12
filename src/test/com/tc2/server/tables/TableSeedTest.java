package test.com.tc2.server.tables;

import com.tc2.server.config.StartupConfig;
import com.tc2.server.service.DatabaseService;
import com.tc2.server.tables.TableSeed;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.TestEnv;

import java.util.Objects;

/**
 * TableSeed Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 12, 2017</pre>
 */
public class TableSeedTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: newId(String key)
     */
    @Test
    public void testNewId() throws Exception {
        StartupConfig startupConfig = new StartupConfig(TestEnv.getDataPath());
        DatabaseService databaseService = new DatabaseService(startupConfig.db);
        TableSeed t = databaseService.tableSeed;

        String prefix = startupConfig.db.seedPrefix;

        Assert.assertTrue(Objects.equals(t.newId("key1"),
                prefix + "key11"));

        Assert.assertTrue(Objects.equals(t.newId("key1"),
                prefix + "key12"));

        Assert.assertTrue(Objects.equals(t.newId("key1"),
                prefix + "key13"));


        Assert.assertTrue(Objects.equals(t.newId("key2"),
                prefix + "key21"));
    }


} 
