package com.tc2.toolkit.thread;

import com.tc2.toolkit.action.Action0;
import com.tc2.toolkit.func.Func0;
import com.tc2.toolkit.print.Console;
import com.tc2.toolkit.helper.TimeHelper;

import java.util.ArrayList;
import java.util.LinkedList;

public class ActiveObject extends Ticker {
    private final static ThreadLocal<ActiveObject> _local = new ThreadLocal<>();

    public static ActiveObject current() {
        return _local.get();
    }

    public static void currentTick(Func0<Boolean> func) {
        ActiveObject current = current();
        if (current == null) {
            Console.log("ActiveObject.current()==null");
        } else {
            current.tick(func);
        }
    }

    private final LinkedList<Action0> _queue = new LinkedList<>();
    private final ArrayList<Func0<Boolean>> _ticks = new ArrayList<>();

    public ActiveObject(int time) {
        super(time);
    }

    private boolean inThread() {
        return current() == this;
    }

    @Override
    protected void onStart() {
        _local.set(this);
    }

    @Override
    protected void onStop() {
    }

    @Override
    protected synchronized void onTick() {
        while (!_queue.isEmpty()) {
            try {
                _queue.removeFirst().invoke();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (_ticks.size() > 0) {
            for (int i = 0; i < _ticks.size(); i++) {
                Func0<Boolean> tick = _ticks.get(i);
                try {
                    if (tick.invoke()) {
                        _ticks.remove(tick);
                        i--;
                    }
                } catch (Exception e) {
                    _ticks.remove(tick);

                    e.printStackTrace();
                }
            }
        }
    }

    public final synchronized void enqueue(Action0 action) {
        _queue.addLast(action);
    }

    public final void invoke(Action0 action) {
        if (inThread()) {
            action.invoke();
        } else {
            enqueue(action);
        }
    }

    public final void tick(Func0<Boolean> func) {
        _ticks.add(func);
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
}
