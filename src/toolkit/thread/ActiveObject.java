package toolkit.thread;

import toolkit.helper.TimeHelper;
import toolkit.lang.Action0;
import toolkit.lang.Func0;
import toolkit.promise.Deferred;
import toolkit.promise.Promise;

import java.util.ArrayList;
import java.util.LinkedList;

public class ActiveObject extends Ticker {
    private final static ThreadLocal<ActiveObject> local = new ThreadLocal<>();

    public static ActiveObject current() {
        return local.get();
    }

    private final LinkedList<Action0> queue = new LinkedList<>();
    private final ArrayList<Func0<Boolean>> ticks = new ArrayList<>();

    private final String name;
    private Deferred startDefer = new Deferred();

    public ActiveObject(int sleep) {
        this(sleep, null);
    }

    public ActiveObject(int sleep, String name) {
        super(sleep);

        this.name = name;
    }

    private boolean inThread() {
        return current() == this;
    }

    public final Promise start() {
        startTicker();
        return startDefer.promise();
    }

    public final void stop() {
        stopTicker();
    }

    @Override
    protected void onStart() {
        local.set(this);
        startDefer.resolve();
    }

    @Override
    protected void onStop() {
    }

    @Override
    protected void onTick() {
        synchronized (queue) {
            while (!queue.isEmpty()) {
                try {
                    queue.removeFirst().invoke();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (ticks) {
            if (ticks.size() > 0) {
                for (int i = 0; i < ticks.size(); i++) {
                    Func0<Boolean> tick = ticks.get(i);
                    try {
                        if (tick.invoke()) {
                            ticks.remove(tick);
                            i--;
                        }
                    } catch (Exception e) {
                        ticks.remove(tick);

                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public final void invoke(Action0 action) {
        if (inThread()) {
            action.invoke();
        } else {
            enqueue(action);
        }
    }

    public final void enqueue(Action0 action) {
        synchronized (queue) {
            queue.addLast(action);
        }
    }

    public final void tick(Func0<Boolean> func) {
        synchronized (ticks) {
            ticks.add(func);
        }
    }

    private class IntervalState {
        long begin = TimeHelper.now();
        long acc = 0;
        boolean cancel = false;
    }

    public final Action0 timeout(Action0 action, int time) {
        IntervalState state = new IntervalState();
        tick(() -> {
            if (TimeHelper.now() - state.begin >= time) {
                action.invoke();
                return true;
            }
            return state.cancel;
        });
        return () -> state.cancel = true;
    }

    public final Action0 interval(Action0 action, int time) {
        IntervalState state = new IntervalState();
        tick(() -> {
            long now = TimeHelper.now();
            long ms = now - state.begin;
            state.begin = now;
            state.acc += ms;
            if (state.acc >= time) {
                state.acc -= time;
                action.invoke();
            }
            return state.cancel;
        });
        return () -> state.cancel = true;
    }

    @Override
    public String toString() {
        return (name != null ? "[" + name + "]" : "")
                + getClass().getSimpleName()
                + "@"
                + super.toString().split("@")[1];
    }
}
