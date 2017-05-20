package com.tc2.toolkit.promise;

import com.tc2.toolkit.action.Action1;

public class Deferred<RESULT> {
    private Promise<RESULT> _promise;

    private Action1<RESULT> _resolve;
    private Action1<Exception> _reject;

    public Deferred() {
        _promise = new Promise<>((resolve, reject) -> {
            _resolve = resolve;
            _reject = reject;
        });
    }

    public Promise<RESULT> promise() {
        return _promise;
    }

    public void resolve(RESULT result) {
        _resolve.invoke(result);
    }

    public void reject(Exception error) {
        _reject.invoke(error);
    }
}
