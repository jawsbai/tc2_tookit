package toolkit.thread;

import toolkit.database.fields.TIME;
import toolkit.helper.TimeHelper;

public abstract class Ticker {
    private final int sleep;
    private final Thread thread;
    private boolean running = false;

    public Ticker(int sleep) {
        this.sleep = sleep;
        thread = new Thread(this::run);
    }

    public final int sleep() {
        return sleep;
    }

    private void run() {
        onStart();
        loop();
        onStop();
    }

    private void loop() {
        long lt = TimeHelper.nowTime();
        while (running) {
            try {
                Thread.sleep(sleep);

                long now = TimeHelper.nowTime();
                long et = now - lt;
                lt = now;
                onTick(et);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected synchronized final void startTicker() {
        if (!running) {
            running = true;
            thread.start();
        }
    }

    protected synchronized final void stopTicker() {
        if (running) {
            running = false;
        }
    }

    protected abstract void onStart();

    protected abstract void onStop();

    protected abstract void onTick(long et);
}
