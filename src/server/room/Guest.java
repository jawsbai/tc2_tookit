package server.room;

import toolkit.print.Console;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;

import java.util.Date;

public abstract class Guest<T extends Room> {
    public final T room;
    public final int queueTime;
    private long beginTime;

    private boolean joined = false;
    private boolean removed = false;

    public Guest(T room, int queueTime) {
        this.room = room;
        this.queueTime = queueTime;
    }

    public final long elapsedTime() {
        return new Date().getTime() - beginTime;
    }

    public final boolean isJoined() {
        return joined;
    }

    public final boolean isRemoved() {
        return removed;
    }

    //add
    protected final Promise<Boolean> addToRoom() {
        Deferred<Boolean> defer = new Deferred<>();
        room.service.invoke(() -> {
            boolean b = room.addGuest(this);
            if (b) {
                onAdded();
            }
            defer.resolve(b);
        });
        return defer.promise();
    }

    protected final Promise<Boolean> removeFromRoom() {
        Deferred<Boolean> defer = new Deferred<>();
        room.service.invoke(() -> {
            boolean b = room.removeGuest(this);
            if (b) {
                onRemoved();
            }
            defer.resolve(b);
        });
        return defer.promise();
    }

    protected void onAdded() {
        beginTime = new Date().getTime();
        Console.log(getClass().getSimpleName(), "onAdded");
    }

    protected void onRemoved() {
        removed = true;
        Console.log(getClass().getSimpleName(), "onRemoved");
    }

    protected void onJoined() {
        Console.log(getClass().getSimpleName(), "joined");
    }

    public void update() {
        if (!joined && elapsedTime() > queueTime) {
            joined = true;
            onJoined();
        }
    }
}
