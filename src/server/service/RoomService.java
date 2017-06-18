package server.service;

import server.room.Room;
import toolkit.lang.Return;
import toolkit.print.Console;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;
import toolkit.thread.ActiveObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class RoomService extends ActiveObject {
    private final ArrayList<Room> rooms = new ArrayList<>();

    public RoomService() {
        super(1000 / 30);
    }

    public boolean addRoom(Room room) {
        if (!rooms.contains(room)) {
            rooms.add(room);
            return true;
        }
        return false;
    }

    public boolean removeRoom(Room room) {
        return rooms.remove(room);
    }

    @Override
    protected void onTick() {
        super.onTick();

        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            room.update();
            if (room.isRemoved()) {
                i--;
            }
        }
    }
}
