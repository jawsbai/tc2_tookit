package server.config;

import server.config.json.UserJson;
import toolkit.helper.FileHelper;

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
