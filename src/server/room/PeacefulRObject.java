package server.room;

import server.hero.IHero;

public class PeacefulRObject<T extends PeacefulRoom> extends RoomObject<T> {
    private final IHero hero;

    public PeacefulRObject(T room, int queueTime, IHero hero) {
        super(room, queueTime);

        this.hero = hero;
    }

    public String getHeroId() {
        return hero.getHeroId();
    }

    public int getFactionId() {
        return hero.getFactionId();
    }

//    protected final Promise<Boolean> addToHeroRoom() {
//        Deferred<Boolean> defer = new Deferred<>();
//        room.service.invoke(() -> {
//            addToRoom().then(b -> {
//                defer.resolve(b);
//            });
//        });
//        return defer.promise();
//    }
//
//    protected final Promise<Boolean> removeFromHeroRoom() {
//        Deferred<Boolean> defer = new Deferred<>();
//        room.service.invoke(() -> {
//            removeFromRoom().then(b -> {
//                defer.resolve(b);
//            });
//        });
//        return defer.promise();
//    }

}
