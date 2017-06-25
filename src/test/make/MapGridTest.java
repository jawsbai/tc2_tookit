package test.make;

import make.MapGrid;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.TestHelper;

/**
 * MapGrid Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 25, 2017</pre>
 */
public class MapGridTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @org.junit.Test
    public void test() throws Exception {
        MapGrid mapGrid = new MapGrid(TestHelper.getConfigPath());
    }
} 
