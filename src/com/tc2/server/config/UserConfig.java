package com.tc2.server.config;

import com.alibaba.fastjson.JSON;
import com.tc2.server.config.json.UserJson;
import com.tc2.toolkit.helper.FileHelper;
import com.tc2.toolkit.print.Console;

public class UserConfig {
    public final UserJson[] users;

    public UserConfig(String folder) throws Exception {
        UserJson[] json = FileHelper.readJson(folder + "/user.json", UserJson[].class);
        if (json == null) {
            throw new Exception("init " + UserConfig.class.getSimpleName() + " error");
        }

        users = json;
    }
}
