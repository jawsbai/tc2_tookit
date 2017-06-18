package test.server.table;

import server.table.TableSeed;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.TestHelper;

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
        TableSeed t = TestHelper.initDatabaseService().tableSeed;

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
