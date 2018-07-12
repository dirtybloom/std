package com.std.engine.gamestate.transition;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by dirtbloom on 07/01/17.
 */
public interface Transition {

    void update(float delta);

    void render(SpriteBatch spriteBatch);

    boolean isFinished();
}
