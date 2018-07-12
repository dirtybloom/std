package com.std.engine.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.std.engine.gamestate.GameState;

/**
 * Created by dirtybloom on 09/01/17.
 */

public interface ISprite extends IUpdatable, IRenderable, ICollidable, Disposable {

    GameState getGameState();

    void setGameState(GameState gameState);

    Vector2 getCenter();

    void setCenter(Vector2 center);

    Vector2 getVelocity();

    void setVelocity(Vector2 velocity);

    void setTextureRegion(TextureRegion textureRegion);

    void setPosition(Vector2 position);

    void hasToBeUpdated(boolean hasToBeUpdated);

    void hasToBeRendered(boolean hasToBeRendered);

    void canCollide(boolean canCollide);

    void dispose();

}
