package server.service.room;

import server.hero.IHero;
import toolkit.lang.Return;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;

public class WarRObject extends RoomObject<WarRoom> {
    private final IHero hero;

    public WarRObject(WarRoom room, int queueTime, IHero hero) {
        super(room, queueTime);

        this.hero = hero;
    }

    public String getHeroId() {
        return hero.getHeroId();
    }

    public int getFactionId() {
        return hero.getFactionId();
    }

    public final Promise<Return> addToWarRoom() {
        Deferred<Return> defer = new Deferred<>();
        room.service.invoke(() -> {
            Return rt = new Return();
            if (room.findWrObject(getHeroId()) != null) {
                rt.setCode(2);
            } else if (room.isEndBuffer()) {
                rt.setCode(3);
            } else if (room.getWrObjects(getFactionId()) >= room.getFactionMaxWrObjects()) {
                rt.setCode(4);
            } else {
                rt.setCode(addToRoom().getResult() ? 0 : 1);
            }
            defer.resolve(rt);
        });
        return defer.promise();
    }

    public final Promise<Boolean> removeFromWarRoom() {
        return removeFromRoom();
    }
}
