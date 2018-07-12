package com.std.engine.utils;

import com.badlogic.gdx.utils.Disposable;
import com.std.engine.entities.IUpdatable;

/**
 * Created by dirtybloom on 14/12/16.
 */

/**
 * GameTimer permette di eseguire delle operazioni allo scadere
 * del tempo limite
 */
public class GameTimer implements IUpdatable, Disposable {

    private GameTimerListener gameTimerListener;

    private float duration, remainingTime;

    private boolean isDisposed = false;

    public GameTimer(float duration, GameTimerListener gameTimerListener) {
        restart(duration);
        this.gameTimerListener = gameTimerListener;
    }

    public void setGameTimerListener(GameTimerListener gameTimerListener) {
        this.gameTimerListener = gameTimerListener;
    }

    @Override
    public boolean isDisposed() {
        return isDisposed;
    }

    @Override
    public boolean hasToBeUpdated() {
        return true;
    }

    @Override
    public void update(float delta) {
        if (remainingTime <= 0) return;//Avoid downflow
        remainingTime -= delta;
        if (remainingTime <= 0 && gameTimerListener != null) {
            gameTimerListener.elapsed();
        }
    }

    public void restart(float duration) {
        if (duration < 0) throw new IllegalArgumentException("duration cannot be less than 0");
        this.duration = duration;
        restart();
    }

    public void restart() {
        remainingTime = duration;
    }

    @Override
    public void dispose() {
        isDisposed = true;
        gameTimerListener = null;
    }

    public interface GameTimerListener {
        void elapsed();
    }
}
