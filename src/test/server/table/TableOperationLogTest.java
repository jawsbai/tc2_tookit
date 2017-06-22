package test.server.table;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpc2j_table.OperationLog;
import server.service.database.table.TableOperationLog;
import test.TestHelper;

import java.util.Date;

/**
 * TableActivityLog Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 18, 2017</pre>
 */
public class TableOperationLogTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testInsert() throws Exception {
        TableOperationLog t = new TableOperationLog(TestHelper.initDatabaseService(), "al1");
        t.createTable();

        boolean insert = t.insert(new OperationLog(0, "r1", "type1", "", new Date()));
        Assert.assertTrue(insert);

        insert = t.insert(new OperationLog(0, "r1", "type1", "", new Date()));
        Assert.assertTrue(insert);

        insert = t.insert(new OperationLog(0, "r1", "type1", "", new Date()));
        Assert.assertTrue(insert);

        insert = t.insert(new OperationLog(0, "r1", "type1", "", new Date()));
        Assert.assertTrue(insert);
    }


} 
