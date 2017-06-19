package toolkit.promise;

import toolkit.lang.Action0;
import toolkit.lang.Action1;
import toolkit.lang.Action2;
import toolkit.lang.Func0;
import toolkit.lang.Func1;
import toolkit.print.Console;
import toolkit.thread.ActiveObject;

public class Promise<RESULT> {
    private static final ActiveObject localAO = new ActiveObject(1, "Promise.localAO");

    static {
        localAO.start();
    }

//    public static Promise all(final Promise... promises) {
//        Deferred defer = new Deferred();
//
//        ActiveObject current = ActiveObject.current();
//        (current != null ? current : ao).tick(() -> {
//            int count = promises.length;
//            for (Promise promise : promises) {
//                if (promise.getState() != PENDING) {
//                    count--;
//
//                    if (promise.getState() == REJECTED) {
//                        defer.reject(promise.getError());
//                        return true;
//                    }
//                }
//            }
//            if (count == 0) {
//                defer.resolve();
//                return true;
//            }
//            return false;
//        });
//        return defer.promise();
//    }

    public static final int PENDING = 0;
    public static final int FULFILLED = 1;
    public static final int REJECTED = 2;

    private final ActiveObject insAO;
    private int state = PENDING;
    private RESULT result;
    private Exception error;

    public Promise(RESULT result) {
        this((resolve, reject) -> resolve.invoke(result));
    }

    public Promise(Action2<Action1<RESULT>, Action1<Exception>> executor) {
        insAO = ActiveObject.current();
        try {
            executor.invoke(this::resolve, this::reject);
        } catch (Exception e) {
            reject(e);
        }
    }

    public int getState() {
        return state;
    }

    public RESULT getResult() {
        return result;
    }

    public Exception getError() {
        return error;
    }

    private synchronized void resolve(RESULT result) {
        if (state == PENDING) {
            this.result = result;
            state = FULFILLED;
        }
    }

    private synchronized void reject(Exception error) {
        if (state == PENDING) {
            this.error = error;
            state = REJECTED;
        }
    }

    private ActiveObject getAO() {
        if (insAO != null) {
            Console.log("insAO", insAO, ActiveObject.current());
            return insAO;
        }
        Console.log("localAO", localAO, ActiveObject.current());
        return localAO;
    }

    private void tickResult(Action0 action) {
        getAO().tick(() -> {
            if (state == PENDING) {
                return false;
            }
            action.invoke();
            return true;
        });
    }

    public <T> Promise<T> then(Class<T> c, Func1<RESULT, Promise<T>> func) {
        Deferred<T> defer = new Deferred<>();
        tickResult(() -> {
            if (state == FULFILLED) {
                try {
                    Promise<T> promise = func.invoke(result);
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
                defer.reject(error);
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
            if (state == REJECTED) {
                action.invoke(error);
            }
        });
        return this;
    }

    public Promise<RESULT> finish(Action0 action) {
        tickResult(action);
        return this;
    }
}
