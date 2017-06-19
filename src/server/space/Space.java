package server.space;

import server.service.SpaceService;
import toolkit.print.Console;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;

import java.util.ArrayList;
import java.util.Date;

public abstract class Space<T extends SpatialObject> {
    public SpaceService service;
    private long beginTime = 0;
    public final long maxTime;
    private boolean removed = false;

    protected final ArrayList<T> objects = new ArrayList<>();

    public Space(SpaceService service, int maxTime) {
        this.service = service;
        this.maxTime = maxTime;
    }

    public final long elapsedTime() {
        return new Date().getTime() - beginTime;
    }

    public boolean isRemoved() {
        return removed;
    }

    //room add remove
    public final Promise<Boolean> addToService() {
        Deferred<Boolean> defer = new Deferred<>();
        service.invoke(() -> {
            boolean b = service.addSpace(this);
            if (b) {
                onAdded();
            }
            defer.resolve(b);
        });
        return defer.promise();
    }

    public final Promise<Boolean> removeFromService() {
        Deferred<Boolean> defer = new Deferred<>();
        service.invoke(() -> {
            boolean b = service.removeSpace(this);
            if (b) {
                onRemoved();
            }
            defer.resolve(b);
        });
        return defer.promise();
    }

    protected void onAdded() {
        beginTime = new Date().getTime();
        Console.log(getClass().getSimpleName(), "onAdded");
    }

    protected void onRemoved() {
        removed = true;
        removeAllObjects();
        Console.log(getClass().getSimpleName(), "onRemoved");
    }

    //so add remove
    public final boolean addObject(T object) {
        if (isRemoved() || objects.contains(object)) {
            return false;
        }
        return objects.add(object);
    }

    public final boolean removeObject(T object) {
        return objects.remove(object);
    }

    protected final void removeAllObjects() {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).removeFromSpace();
            i--;
        }
    }

    public void update() {
        for (int i = 0; i < objects.size(); i++) {
            T object = objects.get(i);
            object.update();
            if (object.isRemoved()) {
                i--;
            }
        }

        if (maxTime > 0 && elapsedTime() > maxTime) {
            removeAllObjects();
            removeFromService();
        }
    }
}
