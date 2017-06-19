package server.space;

import toolkit.print.Console;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;

import java.util.Date;

public class SpatialObject<T extends Space> {
    public final T space;
    public final int queueTime;
    private long beginTime;

    private boolean joined = false;
    private boolean removed = false;

    public SpatialObject(T space, int queueTime) {
        this.space = space;
        this.queueTime = queueTime;
    }

    public final long elapsedTime() {
        return new Date().getTime() - beginTime;
    }

    public final boolean isJoined() {
        return joined;
    }

    public final boolean isRemoved() {
        return removed;
    }

    //add
    protected final Promise<Boolean> addToSpace() {
        Deferred<Boolean> defer = new Deferred<>();
        space.service.invoke(() -> {
            boolean b = space.addObject(this);
            if (b) {
                onAdded();
            }
            defer.resolve(b);
        });
        return defer.promise();
    }

    protected final Promise<Boolean> removeFromSpace() {
        Deferred<Boolean> defer = new Deferred<>();
        space.service.invoke(() -> {
            boolean b = space.removeObject(this);
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
        Console.log(getClass().getSimpleName(), "onRemoved");
    }

    protected void onJoined() {
        joined = true;
        Console.log(getClass().getSimpleName(), "joined");
    }

    public void update() {
        if (!joined && elapsedTime() >= queueTime) {
            onJoined();
        }
    }
}
