package com.std.engine.gamestate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.std.engine.Context;
import com.std.engine.entities.IRenderable;
import com.std.engine.entities.IUpdatable;
import com.std.engine.entities.group.CollisionManager;
import com.std.engine.entities.group.CollisionPair;
import com.std.engine.entities.group.Renderer;
import com.std.engine.entities.group.Updater;

import java.util.List;
import java.util.Map;

/**
 * Created by dirtybloom on 07/01/17.
 */

/**
 * GameState definisce uno stato di gioco, "Menù,Caricamento, ecc..."
 * Ogni GameState deve essere definito in un Context, ed inserito nel GameStateManager.
 * Ogni entità inserita, vivrà solo all'interno del GameState in questione.
 */
public abstract class GameState implements Disposable {

    private final int id;

    protected Context context;

    private Updater updater;
    private Renderer renderer;
    private CollisionManager collisionManager;

    private Map<String, CollisionPair> collisionPairMap;
    private List<CollisionPair> collisionPairList;

    private boolean isReady;

    public GameState(final int id, Context context) {
        this.id = id;
        this.context = context;
        updater = new Updater();
        renderer = new Renderer();
        collisionManager = new CollisionManager();
    }

    public final int getId() {
        return id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void update(float delta) {
        collisionManager.checkCollisions();
        updater.update(delta);
    }

    public void render(SpriteBatch spriteBatch) {
        renderer.render(spriteBatch);
    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {
        updater.dispose();
        renderer.dispose();
        collisionManager.dispose();
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public void addUpdatable(IUpdatable updatable) {
        updater.add(updatable);
    }

    public void addRenderable(IRenderable renderable) {
        renderer.add(renderable);
    }

    public abstract void preTransitionIn();

    public abstract void postTransitionIn();

    public abstract void preTransitionOut();

    public abstract void postTransitionOut();

}
