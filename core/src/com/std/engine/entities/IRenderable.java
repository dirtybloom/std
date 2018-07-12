package com.std.engine.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by dirtybloom on 09/01/17.
 */

public interface IRenderable {

    boolean isDisposed();

    boolean hasToBeRendered();

    void render(SpriteBatch spriteBatch);
}
