package server.service.room;

import java.util.ArrayList;
import java.util.Objects;

public class WarRoom extends Room<WarRObject> {
    private int endBufferTime;
    private final int defenseFactionId;
    private final int factionMaxWrObjects;

    public WarRoom(RoomService service, int maxTime,
                   int endBufferTime,
                   int defenseFactionId,
                   int factionMaxWrObjects) {
        super(service, maxTime);

        this.endBufferTime = endBufferTime;
        this.defenseFactionId = defenseFactionId;
        this.factionMaxWrObjects = factionMaxWrObjects;
    }

    public int getDefenseFactionId() {
        return defenseFactionId;
    }

    public int getFactionMaxWrObjects() {
        return factionMaxWrObjects;
    }

    public boolean isEndBuffer() {
        return maxTime - elapsedTime() <= endBufferTime;
    }

    protected WarRObject findWrObject(String heroId) {
        for (WarRObject object : objects) {
            if (Objects.equals(object.getHeroId(), heroId)) {
                return object;
            }
        }
        return null;
    }

    protected ArrayList<WarRObject> findWrObjects(int factionId) {
        ArrayList<WarRObject> list = new ArrayList<>();
        for (WarRObject obj : objects) {
            if (obj.getFactionId() == factionId) {
                list.add(obj);
            }
        }
        return list;
    }

    protected int getWrObjects(int factionId) {
        int count = 0;
        for (WarRObject obj : objects) {
            if (obj.getFactionId() == factionId) {
                count++;
            }
        }
        return count;
    }
}
