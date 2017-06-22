package server.room;

import toolkit.print.Console;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;

import java.util.Date;

public class RoomObject<T extends Room> {
    public final T room;
    public final int queueTime;
    private long beginTime;

    private boolean joined = false;
    private boolean removed = false;

    public RoomObject(T room, int queueTime) {
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
            boolean b = room.addObject(this);
            if (b) {
                beginTime = new Date().getTime();
                onAdded();
            }
            defer.resolve(b);
        });
        return defer.promise();
    }

    protected final Promise<Boolean> removeFromRoom() {
        Deferred<Boolean> defer = new Deferred<>();
        room.service.invoke(() -> {
            boolean b = room.removeObject(this);
            if (b) {
                removed = true;
                onRemoved();
            }
            defer.resolve(b);
        });
        return defer.promise();
    }

    protected void onAdded() {
        Console.log(getClass().getSimpleName(), "onAdded");
    }

    protected void onRemoved() {
        Console.log(getClass().getSimpleName(), "onRemoved");
    }

    protected void onJoined() {
        joined = true;
        Console.log(getClass().getSimpleName(), "joined");
    }

    public void update() {
        if (!joined && elapsedTime() >= queueTime) {
            onJoined();
        }
    }
}
