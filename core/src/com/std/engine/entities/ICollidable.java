package com.std.engine.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dirtybloom on 09/01/17.
 */

public interface ICollidable {

    TextureRegion getTextureRegion();

    Vector2 getPosition();

    boolean isDisposed();

    boolean canCollide();

    void collided(ICollidable collidable);
}
