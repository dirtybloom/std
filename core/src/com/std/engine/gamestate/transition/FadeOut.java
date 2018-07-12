package com.std.engine.gamestate.transition;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.std.engine.Context;
import com.std.engine.utils.ShapeTextureCache;

/**
 * Created by dirtybloom on 17/12/16.
 */
public class FadeOut implements Transition {

    private Context context;

    private Color color;

    private float duration;

    public FadeOut(Context context, Color color, float duration) {
        this.context = context;
        this.color = color;
        this.duration = duration;
    }

    public FadeOut(Context context, Color color) {
        this(context, color, .5f);
    }

    public FadeOut(Context context) {
        this(context, new Color(0, 0, 0, 0));
    }

    @Override
    public void update(float delta) {
        color.a += (delta * 1f) / duration;

        if (color.a > 1f) color.a = 1f;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        Texture texture = ShapeTextureCache.getInstance().getFilledRectangleTexture(color);
        spriteBatch.draw(texture, context.getOriginX(), context.getOriginY(), context.getWidth(), context.getHeight());
    }

    @Override
    public boolean isFinished() {
        return color.a >= 1f;
    }
}
