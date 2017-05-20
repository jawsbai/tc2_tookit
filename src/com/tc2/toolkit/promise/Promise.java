package com.tc2.toolkit.promise;

import com.tc2.toolkit.action.Action0;
import com.tc2.toolkit.action.Action1;
import com.tc2.toolkit.action.Action2;
import com.tc2.toolkit.func.Func0;
import com.tc2.toolkit.func.Func1;
import com.tc2.toolkit.print.Console;
import com.tc2.toolkit.thread.ActiveObject;

public class Promise<RESULT> {
    public static Promise all(final Promise... promises) {
        Deferred<Object> defer = new Deferred<>();
        ActiveObject.currentTick(() -> {
            int count = promises.length;
            for (Promise promise : promises) {
                if (promise.state() != PENDING) {
                    count--;

                    if (promise.state() == REJECTED) {
                        defer.reject(promise.error());
                        return true;
                    }
                }
            }
            if (count == 0) {
                defer.resolve(null);
                return true;
            }
            return false;
        });
        return defer.promise();
    }

    public static final int PENDING = 0;
    public static final int FULFILLED = 1;
    public static final int REJECTED = 2;

    private int _state = PENDING;
    private RESULT _result;
    private Exception _error;

    public Promise(RESULT result) {
        this((resolve, reject) -> resolve.invoke(result));
    }

    public Promise(Action2<Action1<RESULT>, Action1<Exception>> executor) {
        try {
            executor.invoke(this::resolve, this::reject);
        } catch (Exception e) {
            reject(e);
        }
    }

    public int state() {
        return _state;
    }

    public RESULT result() {
        return _result;
    }

    public Exception error() {
        return _error;
    }

    private synchronized void resolve(RESULT result) {
        if (_state == PENDING) {
            _result = result;
            _state = FULFILLED;
        }
    }

    private synchronized void reject(Exception error) {
        if (_state == PENDING) {
            _error = error;
            _state = REJECTED;
        }
    }

    private void tickResult(Action0 action) {
        ActiveObject.currentTick(() -> {
            if (_state == PENDING) {
                return false;
            }
            action.invoke();
            return true;
        });
    }

    public <T> Promise<T> then(Class<T> c, Func1<RESULT, Promise<T>> func) {
        Deferred<T> defer = new Deferred<>();
        tickResult(() -> {
            if (_state == FULFILLED) {
                try {
                    Promise<T> promise = func.invoke(_result);
                    if (promise == null) {
                        defer.resolve(null);
                    } else {
                        promise.then(c, result -> {
                            defer.resolve(result);
                            return null;
                        }).catch_(defer::reject);
                    }
                } catch (Exception e) {
                    defer.reject(e);
                }
            } else {
                defer.reject(_error);
            }
        });
        return defer.promise();
    }

    public <T> Promise<T> then(Class<T> c, Func0<Promise<T>> func) {
        return then(c, result -> func.invoke());
    }

    public Promise<Object> then(Action1<RESULT> action) {
        return then(Object.class, result -> {
            action.invoke(result);
            return null;
        });
    }

    public Promise<Object> then(Action0 action) {
        return then(Object.class, result -> {
            action.invoke();
            return null;
        });
    }

    public Promise<RESULT> catch_(Action1<Exception> action) {
        tickResult(() -> {
            if (_state == REJECTED) {
                action.invoke(_error);
            }
        });
        return this;
    }

    public Promise<RESULT> finish(Action0 action) {
        tickResult(action);
        return this;
    }
}
