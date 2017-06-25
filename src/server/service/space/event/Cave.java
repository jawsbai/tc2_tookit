package server.service.space.event;

import server.gencode.static_data.MapLabelType;
import server.service.space.Space;

public class Cave extends LabelObject {
    public final String level;

    public Cave(Space space, int x, int y, MapLabelType labelType, String level) {
        super(space, x, y, labelType);
        this.level = level;
    }
}
