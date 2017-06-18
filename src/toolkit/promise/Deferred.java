package toolkit.promise;

import toolkit.lang.Action1;

public class Deferred<RESULT> {
    private Promise<RESULT> promise;

    private Action1<RESULT> resolve;
    private Action1<Exception> reject;

    public Deferred() {
        promise = new Promise<>((resolve, reject) -> {
            this.resolve = resolve;
            this.reject = reject;
        });
    }

    public Promise<RESULT> promise() {
        return promise;
    }

    public void resolve(RESULT result) {
        resolve.invoke(result);
    }

    public void resolve() {
        resolve(null);
    }

    public void reject(Exception error) {
        reject.invoke(error);
    }
}
