package server.service.space.object;

import server.service.space.SpatialObject;
import server.service.space.TerritorySpace;

public class Hero extends SpatialObject<TerritorySpace> {
    public Hero(TerritorySpace space, int x, int y) {
        super(space, x, y);
    }
}
