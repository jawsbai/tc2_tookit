package com.tc2.server.service;

import com.tc2.server.config.UserConfig;
import com.tc2.server.config.json.UserJson;
import com.tc2.server.lang.Return;

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
