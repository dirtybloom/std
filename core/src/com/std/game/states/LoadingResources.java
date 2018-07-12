package com.std.game.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.std.engine.Context;
import com.std.engine.gamestate.GameState;
import com.std.engine.gamestate.transition.FadeIn;
import com.std.engine.gamestate.transition.FadeOut;
import com.std.engine.resources.Assets;
import com.std.engine.utils.ShapeTextureCache;
import com.std.game.STD;

/**
 * Created by dirtybloom on 07/01/17.
 */
public class LoadingResources extends GameState {

    public static final int ID = 0;

    private static final int PROGRESS_BAR_WIDTH = 256;
    private static final int PROGRESS_BAR_HEIGHT = 48;
    private static final int PROGRESS_BAR_LINE_HEIGHT = 4;
    private static final int PROGRESS_BAR_PADDING_X = 4;
    private static final int PROGRESS_BAR_PADDING_Y = 6;

    private static final Color PROGRESS_BAR_COLOR = Color.LIGHT_GRAY;

    private Assets assets = Assets.getInstance();
    private ShapeTextureCache shapeTextureCache = ShapeTextureCache.getInstance();

    private float progress;

    private boolean isReady;

    public LoadingResources(Context context) {
        super(ID, context);
    }

    private void setup() {
        assets.loadTextureAtlas(STD.ATLAS_NAME);
        assets.loadTexture(STD.LOGO);
        assets.loadFreeTypeFont(STD.FONT_FILE_NAME, STD.FONT_SIZE_32);
        assets.loadFreeTypeFont(STD.PLAY_GAME_STATE_FONT, STD.FONT_SIZE_32);
        assets.loadMusic(STD.VENDETTA_VERA_MP3);
        assets.loadMusic(STD.TRINITA_MP3);
        assets.loadMusic(STD.GESU_3_MP3);
        assets.loadTiledMap(STD.TEST_MAP);
        isReady = true;
    }

    @Override
    public void update(float delta) {
        if (!isReady) setup();
        if (!assets.hasDone()) {
            progress = assets.loadNext();
        } else {
            context.getGameStateManager().enterGameState(PlayGameState.ID,new FadeIn(context),new FadeOut(context));
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if (!isReady) setup();
        Texture outer = shapeTextureCache.getRectagleTexture(PROGRESS_BAR_COLOR, PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT, PROGRESS_BAR_LINE_HEIGHT);

        float x = context.getOriginX() + (context.getWidth()- PROGRESS_BAR_WIDTH) / 2;
        float y = context.getOriginY() + (context.getHeight() - PROGRESS_BAR_HEIGHT) / 2;

        spriteBatch.draw(outer, x, y);

        //Adding margins to inner bar
        x = x + (PROGRESS_BAR_LINE_HEIGHT + PROGRESS_BAR_PADDING_X);
        y = y + (PROGRESS_BAR_LINE_HEIGHT + PROGRESS_BAR_PADDING_Y);
        float width = progress * (PROGRESS_BAR_WIDTH - ((PROGRESS_BAR_LINE_HEIGHT + PROGRESS_BAR_PADDING_X) * 2));
        float height = PROGRESS_BAR_HEIGHT - ((PROGRESS_BAR_LINE_HEIGHT + PROGRESS_BAR_PADDING_Y - 1) * 2);

        Texture inner = shapeTextureCache.getFilledRectangleTexture(PROGRESS_BAR_COLOR);
        spriteBatch.draw(inner, x, y, width, height);
    }

    @Override
    public void preTransitionIn() {

    }

    @Override
    public void postTransitionIn() {

    }

    @Override
    public void preTransitionOut() {

    }

    @Override
    public void postTransitionOut() {

    }
}
