package server.service;

import server.space.Space;
import toolkit.thread.ActiveObject;

import java.util.ArrayList;

public class SpaceService extends ActiveObject {
    private final ArrayList<Space> spaces = new ArrayList<>();

    public SpaceService() {
        super(1000 / 30);
    }

    public boolean addSpace(Space space) {
        if (spaces.contains(space)) {
            return false;
        }
        return spaces.add(space);
    }

    public boolean removeSpace(Space space) {
        return spaces.remove(space);
    }

    @Override
    protected void onTick() {
        super.onTick();

        for (int i = 0; i < spaces.size(); i++) {
            Space space = spaces.get(i);
            space.update();
            if (space.isRemoved()) {
                i--;
            }
        }
    }
}
