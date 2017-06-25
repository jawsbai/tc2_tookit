package server.service.space.event;

import server.gencode.static_data.MapLabelType;
import server.service.space.Space;

public class Link extends LabelObject {
    public final String toMapKey;

    public Link(Space space, int x, int y, MapLabelType labelType, String toMapKey) {
        super(space, x, y, labelType);
        this.toMapKey = toMapKey;
    }
}
