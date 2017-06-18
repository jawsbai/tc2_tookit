package server.room;

import server.hero.IHero;
import toolkit.lang.Return;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;

public class WarGuest extends Guest<WarRoom> {
    public final IHero hero;

    public WarGuest(WarRoom room, int queueTime, IHero hero) {
        super(room, queueTime);

        this.hero = hero;
    }

    //add
    public Promise<Return<String>> addToWarRoom() {
        Deferred<Return<String>> defer = new Deferred<>();
        room.service.invoke(() -> {
            Return<String> rt = room.addWarGuest(this);
            if (rt.isSuccess()) {
                onAdded();
            }
            defer.resolve(rt);
        });
        return defer.promise();
    }

    protected final Promise<Boolean> removeFromWarRoom() {
        return removeFromRoom();
    }

    @Override
    protected void onAdded() {
        super.onAdded();
    }

    @Override
    protected void onRemoved() {
        super.onRemoved();
    }

    @Override
    public void update() {
        super.update();
    }
}
