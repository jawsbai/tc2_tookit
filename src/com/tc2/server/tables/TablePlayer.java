package com.tc2.server.tables;

import com.tc2.database.expr.ORDER_BY;
import com.tc2.database.expr.WHERE;
import com.tc2.database.fields.INT;
import com.tc2.database.fields.TIME;
import com.tc2.database.fields.VARCHAR;
import com.tc2.server.config.json.UserJson;
import com.tc2.server.lang.Return;
import com.tc2.server.service.DatabaseService;

public class TablePlayer extends BaseTable {
    private VARCHAR FD_PLAYER_ID;
    private VARCHAR FD_USER_ID;
    private TIME FD_CREATE_TIME;

    private INT FD_LOGGED;
    private INT FD_LOGIN_COUNT;
    private TIME FD_LOGIN_TIME;
    private VARCHAR FD_LOGIN_IP;

    public TablePlayer(DatabaseService service) {
        super(service);
    }

    public void findPlayerByUserId(String userId) {
        select(new WHERE(FD_USER_ID.eq(userId)), new ORDER_BY(), 1, rs -> {

        });
    }

    public boolean userExists(String userId) {
        return exists(new WHERE(FD_USER_ID.eq(userId)));
    }

    public Return<String> createPlayer(String userId) {
        Return<String> rt = new Return<>();
        if (userExists(userId)) {
            rt.setCode(1);
        } else {
            boolean b = insert(
                    FD_PLAYER_ID.eq(service.newId("player")),
                    FD_USER_ID.eq(userId),
                    FD_CREATE_TIME.eqNow(),
                    FD_LOGGED.eq(0),
                    FD_LOGIN_COUNT.eq(0),
                    FD_LOGIN_TIME.eq(0),
                    FD_LOGIN_IP.eq("")
            );
            rt.setCode(b ? 0 : 2);
        }
        return rt;
    }
}
