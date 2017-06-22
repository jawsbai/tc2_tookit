package server.service.room;

import toolkit.helper.TimeHelper;
import toolkit.thread.ActiveObject;

import java.util.ArrayList;

public class RoomService extends ActiveObject {
    private final ArrayList<Room> rooms = new ArrayList<>();
    private long lastTime;

    public RoomService() {
        super(1000 / 30);
    }

    protected boolean addRoom(Room room) {
        return !rooms.contains(room) && rooms.add(room);
    }

    protected boolean removeRoom(Room room) {
        return rooms.remove(room);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lastTime = TimeHelper.nowTime();
    }

    @Override
    protected void onTick(long et) {
        super.onTick(et);

        for (int i = 0; i < rooms.size(); i++) {
            Room item = rooms.get(i);
            item.update(et);
            if (item.isRemoved()) {
                i--;
            }
        }
    }
}
