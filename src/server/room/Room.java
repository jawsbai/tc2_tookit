package server.room;

import server.service.RoomService;
import toolkit.print.Console;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;
import toolkit.thread.ActiveObject;

import java.util.ArrayList;
import java.util.Date;

public abstract class Room<T extends Guest> {
    public final RoomService service;
    private long beginTime = 0;
    public final long maxTime;

    protected final ArrayList<T> guests = new ArrayList<>();

    private boolean removed = false;

    public Room(RoomService service, int maxTime) {
        this.service = service;
        this.maxTime = maxTime;
    }

    public final long elapsedTime() {
        return new Date().getTime() - beginTime;
    }

    public final boolean isRemoved() {
        return removed;
    }

    //room add remove
    public final Promise<Boolean> addToService() {
        Deferred<Boolean> defer = new Deferred<>();
        service.invoke(() -> {
            boolean b = service.addRoom(this);
            if (b) {
                onAdded();
            }
            defer.resolve(b);
        });
        return defer.promise();
    }

    public final Promise<Boolean> removeFromService() {
        Deferred<Boolean> defer = new Deferred<>();
        service.invoke(() -> {
            boolean b = service.removeRoom(this);
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

    private void removeGuests() {
        for (int i = 0; i < guests.size(); i++) {
            guests.get(i).removeFromRoom();
            i--;
        }
    }

    //guest add remove
    protected final boolean addGuest(T guest) {
        if (!guests.contains(guest)) {
            guests.add(guest);
            return true;
        }
        return false;
    }

    protected final boolean removeGuest(T guest) {
        return guests.remove(guest);
    }

    public void update() {
        for (int i = 0; i < guests.size(); i++) {
            Guest guest = guests.get(i);
            guest.update();
            if (guest.isRemoved()) {
                i--;
            }
        }

        if (elapsedTime() > maxTime) {
            removeGuests();
            removeFromService();
        }
    }
}
