package com.std.engine.gamestate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.std.engine.gamestate.transition.NullTransition;
import com.std.engine.gamestate.transition.Transition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dirtybloom on 07/01/17.
 */

/**
 * GameStateManager permette di gestire le transizioni tra i vari GameState,
 * cosa più importante, permette di tenere traccia di tutti i GameState inseriti
 *
 * @param <T>
 */
public class GameStateManager<T extends GameState> implements Disposable {

    private T currentGameState, nextGameState;
    private Map<Integer, T> gameStateMap;
    private Transition transitionIn, transitionOut;

    public GameStateManager() {
        gameStateMap = new ConcurrentHashMap<Integer, T>();
    }

    public void update(float delta) {
        if (transitionOut != null) {
            transitionOut.update(delta);
            if (transitionOut.isFinished()) {
                if (currentGameState != null) {
                    currentGameState.postTransitionOut();
                }
                currentGameState = nextGameState;
                nextGameState = null;
                transitionOut = null;

                if (transitionIn != null) {
                    currentGameState.preTransitionIn();
                }
            } else {
                return;
            }
        }

        if (transitionIn != null) {
            transitionIn.update(delta);
            if (transitionIn.isFinished()) {
                currentGameState.postTransitionIn();
                transitionIn = null;
            } else {
                return;
            }
        }
        if (currentGameState != null) {
            currentGameState.update(delta);
        }
    }

    public void render(SpriteBatch spriteBatch) {
        if (currentGameState != null) {
            currentGameState.render(spriteBatch);
        }

        if (transitionOut != null) {
            transitionOut.render(spriteBatch);
        } else if (transitionIn != null) {
            transitionIn.render(spriteBatch);
        }
    }

    public void pause() {
        if (currentGameState != null) currentGameState.pause();
    }

    public void resume() {
        if (currentGameState != null) currentGameState.resume();
    }

    @Override
    public void dispose() {
        for (Integer key : gameStateMap.keySet()) {
            gameStateMap.get(key).dispose();
        }
        gameStateMap.clear();
    }

    public void enterGameState(int id, Transition transitionIn, Transition transitionOut) {
        if (transitionIn == null) transitionIn = new NullTransition();
        if (transitionOut == null) transitionOut = new NullTransition();

        this.transitionIn = transitionIn;
        this.transitionOut = transitionOut;

        this.nextGameState = getGameState(id);

        if (currentGameState != null) {
            currentGameState.preTransitionOut();
        }
    }

    public void addGameState(T t) {
        gameStateMap.put(t.getId(), t);
    }

    public T getGameState(int id) {
        return gameStateMap.get(id);
    }

    public boolean isTransitioning() {
        return transitionIn != null || transitionOut != null;
    }
}
