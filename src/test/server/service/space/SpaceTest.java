package test.server.service.space;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import server.gencode.static_data.MapLabelType;
import server.service.space.Space;
import server.service.space.event.LabelObject;
import server.service.static_data.StaticDataService;
import test.TestHelper;
import toolkit.print.Console;

public class SpaceTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: update(long et)
     */
    @Test
    public void test() throws Exception {
        StaticDataService s = new StaticDataService(TestHelper.getConfigPath());

        Space a1 = new Space(s.loadMap("A1"));
        Assert.assertTrue(a1.findLabels(MapLabelType.HEROBORNPOINT).size() == 1);
        Assert.assertTrue(a1.findLabel(MapLabelType.HEROBORNPOINT) != null);
        Assert.assertTrue(a1.findLabels(MapLabelType.CAVE).size() == 2);
        Assert.assertTrue(a1.findLabels(MapLabelType.FACTORY).size() == 1);
        Assert.assertTrue(a1.findLabels(MapLabelType.POC).size() == 0);
        Assert.assertTrue(a1.findLabels(MapLabelType.LINK).size() == 2);
        LabelObject bornPoint = a1.findLabel(MapLabelType.HEROBORNPOINT);
        Console.log(bornPoint.getX(), bornPoint.getY());

        Space b1 = new Space(s.loadMap("B1"));
        Assert.assertTrue(b1.findLabels(MapLabelType.HEROBORNPOINT).size() == 0);
        Assert.assertTrue(b1.findLabels(MapLabelType.CAVE).size() == 3);
        Assert.assertTrue(b1.findLabels(MapLabelType.POC).size() == 1);
        Assert.assertTrue(b1.findLabels(MapLabelType.LINK).size() == 3);


    }


} 
