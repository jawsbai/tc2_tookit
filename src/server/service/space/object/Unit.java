package server.service.space.object;

import server.service.space.SpatialObject;
import server.service.space.WarSpace;

public class Unit extends SpatialObject<WarSpace> {
    public Unit(WarSpace space, int x, int y) {
        super(space, x, y);
    }
}
