package com.tc2.toolkit.thread;

public abstract class Ticker {
    private final int _time;
    private final Thread _thread;
    private boolean _running = true;

    public Ticker(int time) {
        _time = time;
        _thread = new Thread(this::run);
    }

    public final int time() {
        return _time;
    }

    private void run() {
        onStart();
        loop();
        onStop();
    }

    private void loop() {
        while (_running) {
            try {
                Thread.sleep(_time);

                onTick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public final void start() {
        if (_running) {
            _thread.start();
        }
    }

    public final void stop() {
        _running = false;
    }

    protected abstract void onStart();

    protected abstract void onStop();

    protected abstract void onTick();
}
