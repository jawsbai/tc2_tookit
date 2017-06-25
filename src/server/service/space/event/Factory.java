package server.service.space.event;

import server.gencode.static_data.MapLabelType;
import server.service.space.Space;

public class Factory extends LabelObject {
    public Factory(Space space, int x, int y, MapLabelType labelType) {
        super(space, x, y, labelType);
    }
}
