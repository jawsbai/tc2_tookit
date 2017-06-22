package server.service.room;

import server.hero.IHero;
import toolkit.lang.Return;
import toolkit.print.Console;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;

import java.math.RoundingMode;

public class TerritoryRObject extends RoomObject<TerritoryRoom> {
    private final IHero hero;

    public TerritoryRObject(TerritoryRoom room, int queueTime, IHero hero) {
        super(room, queueTime);

        this.hero = hero;
    }

    public String getHeroId() {
        return hero.getHeroId();
    }

    public int getFactionId() {
        return hero.getFactionId();
    }

    public final Promise<Return> addToTerritoryRoom() {
        Deferred<Return> defer = new Deferred<>();
        room.service.invoke(() -> {
            Return rt = new Return();
            if (room.findTrObject(getHeroId()) != null) {
                rt.setCode(1);
            } else if (room.getFactionLockTime() > 0 && getFactionId() != room.getOwnerFactionId()) {
                rt.setCode(2);
            } else {
                rt.setCode(addToRoom().getResult() ? 0 : 3);
            }
            defer.resolve(rt);
        });
        return defer.promise();
    }

    public final Promise<Boolean> removeFromTerritoryRoom() {
        return removeFromRoom();
    }

}
