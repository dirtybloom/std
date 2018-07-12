package com.std.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.std.engine.entities.AbstractSprite;
import com.std.engine.gamestate.GameState;

public class Player extends AbstractSprite implements InputProcessor {

    private PositionChangeLister positionChangeLister;

    public Player(GameState gameState) {
        super(gameState);
    }

    public void setPositionChangeLister(PositionChangeLister positionChangeLister) {
        this.positionChangeLister = positionChangeLister;
    }

    @Override
    public void update(float delta) {

        Vector2 position = getPosition();

        float old_x = position.x, old_y = position.y;

        super.update(delta);

        position = getPosition();

        if (positionChangeLister != null) {
            if (position.x != old_x || position.y != old_y)
                positionChangeLister.onPositionChanged(position.x, position.y);
        }
    }

    @Override
    public boolean keyDown(int keycode) {


        switch (keycode) {
            case Input.Keys.A:

                getVelocity().set(-120, 0);
                break;
            case Input.Keys.D:
                getVelocity().set(120, 0);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.D:
                getVelocity().set(0, 0);
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public interface PositionChangeLister {
        void onPositionChanged(float x, float y);
    }
}
