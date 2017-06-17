package server.service;

import server.config.UserConfig;
import server.config.json.UserJson;
import toolkit.lang.Return;

import java.util.Objects;

public class AccountService {
    private final UserConfig userConfig;

    public AccountService(UserConfig config) {
        userConfig = config;
    }

    private UserJson findUserByUserName(String userName) {
        for (UserJson user : userConfig.users) {
            if (Objects.equals(user.userName, userName)) {
                return user;
            }
        }
        return null;
    }

    public Return<UserJson> login(String userName, String password) {
        Return<UserJson> rt = new Return<>();
        UserJson user = findUserByUserName(userName);
        if (user == null) {
            rt.setCode(1);
        } else if (!Objects.equals(user.password, password)) {
            rt.setCode(2);
        } else {
            rt.setValue(user);
        }
        return rt;
    }
}
