package test.server.service;

import org.junit.After;
import org.junit.Before;
import server.config.ConfigLoader;
import server.config.json.GalaxyJson;
import server.service.GalaxyService;
import test.TestEnv;

import java.util.HashMap;

/**
 * GalaxyService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 18, 2017</pre>
 */
public class GalaxyServiceTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @org.junit.Test
    public void test() throws Exception {
        ConfigLoader configLoader = new ConfigLoader(TestEnv.getConfigPath());
        GalaxyJson galaxyJson = configLoader.load(GalaxyJson.class, "/galaxy.json");
        GalaxyService s = new GalaxyService(galaxyJson);
        s.start();
    }

} 
