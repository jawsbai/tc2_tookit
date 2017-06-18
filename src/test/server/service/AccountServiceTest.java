package test.server.service;

import server.config.ConfigLoader;
import server.config.json.AccountJson;
import server.config.json.UserJson;
import toolkit.lang.Return;
import server.service.AccountService;
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

    @org.junit.Test
    public void test() throws Exception {
        ConfigLoader configLoader = new ConfigLoader(TestEnv.getConfigPath());
        AccountJson accountJson = configLoader.load(AccountJson.class, "/account.json");
        AccountService accountService = new AccountService(accountJson);

        Return<UserJson> rt;

        rt = accountService.login("test1", "1234562");
        Assert.assertTrue(rt.isError());

        rt = accountService.login("222", "123456");
        Assert.assertTrue(rt.isError());

        rt = accountService.login("test1", "123456");
        Assert.assertTrue(rt.isSuccess());
    }

} 
