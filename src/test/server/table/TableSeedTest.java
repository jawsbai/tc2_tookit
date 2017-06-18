package test.server.table;

import server.service.DatabaseService;
import server.table.TableSeed;
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
        TableSeed t = TestEnv.initDatabaseService().tableSeed;

        String prefix = "solar";

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
