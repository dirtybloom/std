package com.std.engine.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by dirtybloom on 09/01/17.
 */
public class CameraDebugger {

    private Sprite minimumAreaSprite;
    private Sprite maximumAreaSprite;
    private BitmapFont font;

    private float minWidth, minHeight, maxWidth, maxHeight;

    private boolean isReady;

    public CameraDebugger(float minWidth, float minHeight, float maxWidth, float maxHeight) {
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public void setup() {
        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(0, 0, 64, 64);

        minimumAreaSprite = new Sprite(new Texture(pixmap));
        minimumAreaSprite.setPosition(-minWidth / 2, -minHeight / 2);
        minimumAreaSprite.setSize(minWidth, minHeight);
        minimumAreaSprite.setColor(0f, 1f, 0f, 1f);

        maximumAreaSprite = new Sprite(new Texture(pixmap));
        maximumAreaSprite.setPosition(-maxWidth / 2, -maxHeight / 2);
        maximumAreaSprite.setSize(maxWidth, maxHeight);
        maximumAreaSprite.setColor(1f, 1f, 0f, 1f);

        font = new BitmapFont();
        font.setColor(Color.BLACK);

        isReady = true;
    }

    public void render(SpriteBatch spriteBatch) {
        if (!isReady) setup();
        maximumAreaSprite.draw(spriteBatch);
        minimumAreaSprite.draw(spriteBatch);
        font.draw(spriteBatch, String.format("%1$sx%2$s", Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), -20, 0);
    }
}
