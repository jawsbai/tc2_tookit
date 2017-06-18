package server.room;

import server.service.RoomService;

public class WarRoom extends Room {
    public final int factionMaxHeros;

    public WarRoom(RoomService service, int maxTime, int factionMaxHeros) {
        super(service, maxTime);

        this.factionMaxHeros = factionMaxHeros;
    }

    @Override
    protected void onAdded() {
        super.onAdded();
    }

    @Override
    protected void onRemoved() {
        super.onRemoved();
    }

    @Override
    public void update() {
        super.update();
    }
}
