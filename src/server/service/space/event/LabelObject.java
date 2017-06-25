package server.service.space.event;

import server.gencode.static_data.MapLabelType;
import server.service.space.Space;
import server.service.space.SpatialObject;

public class LabelObject extends SpatialObject {
    public final MapLabelType labelType;

    public LabelObject(Space space, int x, int y, MapLabelType labelType) {
        super(space, x, y);
        this.labelType = labelType;
    }
}
