package com.std.engine.utils;

/**
 * Created by dirtybloom on 14/12/16.
 */
public class Timer {

    private long startTime, endTime, timeSlice;

    public Timer(long timeSlice) {
        restart(timeSlice);
    }

    public long timeToEnd() {
        return endTime - System.currentTimeMillis();
    }

    public boolean isElapsed() {
        return timeToEnd() <= 0;
    }

    public void restart(long timeSlice) {
        this.timeSlice = timeSlice;
        restart();
    }

    public void restart() {
        startTime = System.currentTimeMillis();
        endTime = startTime + timeSlice;
    }
}
