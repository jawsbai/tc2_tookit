package server.tables;

import rpc2j_table.Player;
import server.service.DatabaseService;
import toolkit.database.expr.ORDER_BY;
import toolkit.database.expr.WHERE;
import toolkit.lang.Return;

public class TablePlayer extends ServiceTable {
    public TablePlayer(DatabaseService service) {
        super(service, Player.newTableDefined());
    }

    public Player findPlayerByUserId(String userId) {
        final Player[] player = new Player[1];
        select(new WHERE(Player.FD_USERID.eq(userId)), new ORDER_BY(), 1, rs -> {
            player[0] = Player.newFromRS(rs);
        });
        return player[0];
    }

    public boolean userExists(String userId) {
        return exists(new WHERE(Player.FD_USERID.eq(userId)));
    }

    public Return<String> createPlayer(String userId) {
        Return<String> rt = new Return<>();
        if (userExists(userId)) {
            rt.setCode(1);
        } else {
            boolean b = insert(
                    Player.FD_PLAYERID.eq(service.newId(Player.class.getSimpleName())),
                    Player.FD_USERID.eq(userId),
                    Player.FD_CREATETIME.eqNow(),
                    Player.FD_ONLINE.eqFalse()
            );
            rt.setCode(b ? 0 : 2);
        }
        return rt;
    }
}
