package server.service.room;

import java.util.Objects;

public class TerritoryRoom extends Room<TerritoryRObject> {
    private int ownerFactionId;
    private long factionLockTime;

    public TerritoryRoom(RoomService service, int maxTime,
                         int ownerFactionId,
                         int factionLockTime) {
        super(service, maxTime);

        this.ownerFactionId = ownerFactionId;
        this.factionLockTime = factionLockTime;
    }

    public int getOwnerFactionId() {
        return ownerFactionId;
    }

    public long getFactionLockTime() {
        return factionLockTime;
    }

    protected TerritoryRObject findTrObject(String heroId) {
        for (TerritoryRObject object : objects) {
            if (Objects.equals(object.getHeroId(), heroId)) {
                return object;
            }
        }
        return null;
    }

    @Override
    public void update(long et) {
        super.update(et);

        if (factionLockTime > 0) {
            factionLockTime = Math.max(0, factionLockTime - et);
        }
    }
}
