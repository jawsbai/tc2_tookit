package test.com.tc2.server.service;

import com.tc2.server.config.UserConfig;
import com.tc2.server.config.json.UserJson;
import com.tc2.server.lang.Return;
import com.tc2.server.service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import test.TestEnv;

/**
 * AccountService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 10, 2017</pre>
 */
public class AccountServiceTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: login(String userName, String password)
     */
    @org.junit.Test
    public void testLogin() throws Exception {
        UserConfig userConfig = new UserConfig(TestEnv.getDataPath());
        AccountService accountService = new AccountService(userConfig);

        Return<UserJson> rt;

        rt = accountService.login("test1", "1234562");
        Assert.assertTrue(rt.isError());

        rt = accountService.login("222", "123456");
        Assert.assertTrue(rt.isError());

        rt = accountService.login("test1", "123456");
        Assert.assertTrue(rt.isSuccess());
    }

} 
