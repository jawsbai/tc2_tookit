package server.service;

import server.room.Room;
import toolkit.thread.ActiveObject;

import java.util.ArrayList;

public class RoomService extends ActiveObject {
    private final ArrayList<Room> rooms = new ArrayList<>();

    public RoomService() {
        super(1000 / 30);
    }

    public boolean addRoom(Room room) {
        if (rooms.contains(room)) {
            return false;
        }
        return rooms.add(room);
    }

    public boolean removeRoom(Room room) {
        return rooms.remove(room);
    }

    @Override
    protected void onTick() {
        super.onTick();

        for (int i = 0; i < rooms.size(); i++) {
            Room item = rooms.get(i);
            item.update();
            if (item.isRemoved()) {
                i--;
            }
        }
    }
}
