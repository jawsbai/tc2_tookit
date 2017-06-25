package server.service.database.table;

import server.gencode.table.Hero;
import server.service.database.DatabaseService;
import toolkit.database.expr.ORDER_BY;
import toolkit.database.expr.WHERE;
import toolkit.lang.Return;

import java.util.ArrayList;

public class TableHero extends ServiceTable {
    public TableHero(DatabaseService service) {
        super(service, Hero.newTableDefined());
    }

    public ArrayList<Hero> findHerosByPlayerId(String playerId) {
        ArrayList<Hero> list = new ArrayList<>();
        select(new WHERE(Hero.FD_PLAYERID.eq(playerId)), new ORDER_BY(Hero.FD_CREATETIME), rs -> {
            list.add(Hero.newFromRS(rs));
        });
        return list;
    }

    public Hero findHero(String heroId) {
        final Hero[] hero = new Hero[1];
        select(new WHERE(Hero.FD_HEROID.eq(heroId)), new ORDER_BY(), 1, rs -> {
            hero[0] = Hero.newFromRS(rs);
        });
        return hero[0];
    }

    public int countByPlayerId(String playerId) {
        return count(new WHERE(Hero.FD_PLAYERID.eq(playerId)));
    }

    public boolean nicknameExists(String nickname) {
        return exists(new WHERE(Hero.FD_NICKNAME.eq(nickname)));
    }

    public Return<String> createHero(Hero table) {
        Return<String> rt = new Return<>();
        if (nicknameExists(table.getNickname())) {
            rt.setCode(1);
        } else {
            table.setHeroId(service.newId(Hero.class.getSimpleName()));
            boolean b = insert(table.toEQS());
            rt.setCode(b ? 0 : 2);
        }
        return rt;
    }
}
