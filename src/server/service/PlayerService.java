package server.service;

import toolkit.lang.Return;
import toolkit.thread.ActiveObject;

public class PlayerService extends ActiveObject {
    public PlayerService() {
        super(1000 / 30);
    }

    public Return<String> login(String userName, String password) {
        return null;
    }
}
