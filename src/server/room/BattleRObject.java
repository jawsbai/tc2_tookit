package server.room;

import server.hero.IHero;

public class BattleRObject extends RoomObject<BattleRoom> {
    private final IHero hero;

    public BattleRObject(BattleRoom room, int queueTime, IHero hero) {
        super(room, queueTime);

        this.hero = hero;
    }

    public String getHeroId() {
        return hero.getHeroId();
    }

    public int getFactionId() {
        return hero.getFactionId();
    }

    //add
//    public Promise<Return<String>> addToWarRoom() {
//        Deferred<Return<String>> defer = new Deferred<>();
//        room.service.invoke(() -> {
//            Return<String> rt = room.addWarGuest(this);
//            if (rt.isSuccess()) {
//                onAdded();
//            }
//            defer.resolve(rt);
//        });
//        return defer.promise();
//    }

//    public final Promise<Boolean> removeFromWarSpace() {
//        return removeFromRoom();
//    }

}
