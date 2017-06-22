package server.room;

import server.service.RoomService;
import toolkit.print.Console;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;

import java.util.ArrayList;
import java.util.Date;

public abstract class Room<T extends RoomObject> {
    public RoomService service;
    private long beginTime = 0;
    public final long maxTime;
    private boolean removed = false;

    protected final ArrayList<T> objects = new ArrayList<>();

    public Room(RoomService service, int maxTime) {
        this.service = service;
        this.maxTime = maxTime;
    }

    public final long elapsedTime() {
        return new Date().getTime() - beginTime;
    }

    public boolean isRemoved() {
        return removed;
    }

    //room add remove
    public final Promise<Boolean> addToRoom() {
        Deferred<Boolean> defer = new Deferred<>();
        service.invoke(() -> {
            boolean b = service.addRoom(this);
            if (b) {
                beginTime = new Date().getTime();
                onAdded();
            }
            defer.resolve(b);
        });
        return defer.promise();
    }

    public final Promise<Boolean> removeFromRoom() {
        Deferred<Boolean> defer = new Deferred<>();
        service.invoke(() -> {
            boolean b = service.removeRoom(this);
            if (b) {
                removeLodgers();
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

    //so add remove
    public final boolean addObject(T object) {
        if (isRemoved() || objects.contains(object)) {
            return false;
        }
        return objects.add(object);
    }

    public final boolean removeObject(T object) {
        return objects.remove(object);
    }

    protected final void removeLodgers() {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).removeFromRoom();
            i--;
        }
    }

    public void update() {
        for (int i = 0; i < objects.size(); i++) {
            T item = objects.get(i);
            item.update();
            if (item.isRemoved()) {
                i--;
            }
        }

        if (maxTime > 0 && elapsedTime() > maxTime) {
            removeFromRoom();
        }
    }
}
