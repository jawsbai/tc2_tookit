package server.room;

import server.service.RoomService;

public abstract class PeacefulRoom extends Room<PeacefulRObject> {
    public PeacefulRoom(RoomService service, int maxTime) {
        super(service, maxTime);
    }

    protected final boolean addPrObject(PeacefulRObject prObject) {
        return addObject(prObject);
    }

    protected final boolean removePrObject(PeacefulRObject prObject) {
        return removeObject(prObject);
    }
}
