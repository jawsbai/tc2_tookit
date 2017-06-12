package com.tc2.server.tables;

import com.tc2.database.expr.ORDER_BY;
import com.tc2.database.expr.WHERE;
import com.tc2.database.fields.INT;
import com.tc2.database.fields.JSON;
import com.tc2.database.fields.TIME;
import com.tc2.database.fields.VARCHAR;
import com.tc2.server.lang.Return;
import com.tc2.server.service.DatabaseService;

public class TableHero extends BaseTable {
    private VARCHAR FD_HERO_ID;
    private VARCHAR FD_PLAYER_ID;
    private TIME FD_CREATE_TIME;

    private VARCHAR FD_NICKNAME;
    private INT FD_PLAYING;

    private INT FD_GALAXY_ID;
    private INT FD_PLANET_ID;
    private INT FD_COUNTRY_ID;
    private VARCHAR FD_AT;

    private JSON FD_DEPLOYS;

    private INT FD_MONEY;
    private INT FD_ORE;//铁
    private INT FD_URANIUM;//铀
    private INT FD_SULFUR;//硫磺
    private INT FD_SILICON;//硅

    public TableHero(DatabaseService service) {
        super(service);
    }

    public void findHerosByPlayerId(String playerId) {
        select(new WHERE(FD_PLAYER_ID.eq(playerId)), new ORDER_BY(FD_CREATE_TIME), rs -> {

        });
    }

    public void findHero(String heroId) {

    }

    public int countByPlayerId(String playerId) {
        return count(new WHERE(FD_PLAYER_ID.eq(playerId)));
    }

    public boolean nicknameExists(String nickname) {
        return exists(new WHERE(FD_NICKNAME.eq(nickname)));
    }

    public Return<String> createHero(String playerId,
                                     String nickname,
                                     int galaxyId,
                                     int planetId,
                                     int countryId,
                                     String at,
                                     int money,
                                     int ore,
                                     int uranium,
                                     int sulfur,
                                     int silicon) {
        Return<String> rt = new Return<>();
        if (nicknameExists(nickname)) {
            rt.setCode(1);
        } else {
            boolean b = insert(
                    FD_HERO_ID.eq(service.newId("hero")),
                    FD_PLAYER_ID.eq(playerId),
                    FD_CREATE_TIME.eqNow(),
                    FD_NICKNAME.eq(nickname),
                    FD_PLAYING.eq(0),
                    FD_GALAXY_ID.eq(galaxyId),
                    FD_PLANET_ID.eq(planetId),
                    FD_COUNTRY_ID.eq(countryId),
                    FD_AT.eq(at),
                    FD_DEPLOYS.eqJSON(new String[]{}),
                    FD_MONEY.eq(money),
                    FD_ORE.eq(ore),
                    FD_URANIUM.eq(uranium),
                    FD_SULFUR.eq(sulfur),
                    FD_SILICON.eq(silicon)
            );
            rt.setCode(b ? 0 : 2);
        }
        return rt;
    }
}
