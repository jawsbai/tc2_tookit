package server.room;

import server.hero.IHero;
import toolkit.lang.Return;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;

public class HeroGuest extends Guest {
    public final IHero hero;

    public HeroGuest(Room room, int queueTime, IHero hero) {
        super(room, queueTime);

        this.hero = hero;
    }

    //add
    public Promise<Return<String>> addToWarRoom() {
        Deferred<Return<String>> defer = new Deferred<>();
        room.service.invoke(() -> {
            Return<String> rt = new Return<>();
            boolean b = room.addGuest(this);
            if (b) {
                onAdded();
            }
            defer.resolve(rt);
        });
        return defer.promise();
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
