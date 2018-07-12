package com.std.engine.gamestate.transition;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by dirtybloom on 07/01/17.
 */
public class NullTransition implements Transition {
    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
