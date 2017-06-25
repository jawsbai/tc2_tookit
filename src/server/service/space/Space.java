package server.service.space;

import server.gencode.static_data.Map;
import server.gencode.static_data.MapLabel;
import server.gencode.static_data.MapLabelType;
import server.service.space.event.*;

import java.util.ArrayList;

public class Space {
    public final String key;
    public final int width;
    public final int height;
    public final int cellSize;

    public final int pixWidth;
    public final int pixHeight;

    public final LabelObject[] labels;

    public Space(Map map) {
        key = map.key;
        width = map.width;
        height = map.height;
        cellSize = map.cellSize;

        pixWidth = width * cellSize;
        pixHeight = height * cellSize;

        labels = loadLabels(map.labels);
    }

    private LabelObject[] loadLabels(MapLabel[] labels) {
        int len = labels.length;
        LabelObject[] labelObjects = new LabelObject[len];
        for (int i = 0; i < len; i++) {
            LabelObject labelObject = createLabelObject(labels[i]);
            labelObjects[i] = labelObject;
        }
        return labelObjects;
    }

    private LabelObject createLabelObject(MapLabel label) {
        MapLabelType type = MapLabelType.getByValue(label.type);
        if (type == MapLabelType.FACTORY) {
            return new Factory(this, label.x, label.y, type);
        } else if (type == MapLabelType.CAVE) {
            return new Cave(this, label.x, label.y, type, label.value);
        } else if (type == MapLabelType.POC) {
            return new Poc(this, label.x, label.y, type);
        } else if (type == MapLabelType.LINK) {
            return new Link(this, label.x, label.y, type, label.value);
        }
        return new LabelObject(this, label.x, label.y, type);
    }

    public ArrayList<LabelObject> findLabels(MapLabelType labelType) {
        ArrayList<LabelObject> labelObjects = new ArrayList<>();
        for (LabelObject label : labels) {
            if (label.labelType == labelType) {
                labelObjects.add(label);
            }
        }
        return labelObjects;
    }

    public LabelObject findLabel(MapLabelType labelType) {
        ArrayList<LabelObject> labels = findLabels(labelType);
        return labels.size() > 0 ? labels.get(0) : null;
    }

    public void update(long et) {

    }
}
