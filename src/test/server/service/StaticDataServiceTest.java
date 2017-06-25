package test.server.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import server.gencode.static_data.Map;
import server.service.static_data.StaticDataService;
import test.TestHelper;
import toolkit.print.Console;

public class StaticDataServiceTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: loadMap(String mapKey)
     */
    @Test
    public void testLoadMap() throws Exception {
        StaticDataService s = new StaticDataService(TestHelper.getConfigPath());
        Map map = s.loadMap("A1");
        Assert.assertTrue(map != null);

        Assert.assertTrue(s.loadMap("ssss") == null);
    }


} 
