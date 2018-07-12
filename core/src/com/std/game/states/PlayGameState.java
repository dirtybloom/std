package com.std.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.std.engine.Context;
import com.std.engine.gamestate.GameState;
import com.std.engine.resources.Assets;
import com.std.game.STD;
import com.std.game.entities.Player;

/**
 * Created by dirtybloom on 07/01/17.
 */
public class PlayGameState extends GameState implements Player.PositionChangeLister {

    public static final int ID = 2;

    public PlayGameState(Context context) {
        super(ID, context);
    }

    public Music vendetta_vera,trinita,gesu_3;

    private Music [] musics;

    private int currentMusic = 2;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    private Player player;

    private Music.OnCompletionListener onCompletionListener = new Music.OnCompletionListener() {
        @Override
        public void onCompletion(Music music) {
            currentMusic = (currentMusic + 1) % musics.length;
            musics[currentMusic].play();
        }
    };

    @Override
    public void preTransitionIn() {
        vendetta_vera = Assets.getInstance().getMusic(STD.VENDETTA_VERA_MP3);
        trinita = Assets.getInstance().getMusic(STD.TRINITA_MP3);
        gesu_3 = Assets.getInstance().getMusic(STD.GESU_3_MP3);

        musics = new Music[3];
        musics[0] = vendetta_vera;
        musics[1] = trinita;
        musics[2] = gesu_3;

        vendetta_vera.setOnCompletionListener(onCompletionListener);
        trinita.setOnCompletionListener(onCompletionListener);
        gesu_3.setOnCompletionListener(onCompletionListener);

        tiledMap = Assets.getInstance().getMap(STD.TEST_MAP);

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap,2);

    }

    @Override
    public void postTransitionIn() {

        gesu_3.play();
        context.getCamera().translate(context.getWidth()/2,context.getHeight()/2);

        player = new Player(this);
        player.setPositionChangeLister(this);
        Gdx.input.setInputProcessor(player);

        player.setPosition(new Vector2(context.getCamera().position.x,context.getCamera().position.y));

        addUpdatable(player);
    }

    @Override
    public void preTransitionOut() {

    }

    @Override
    public void postTransitionOut() {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);

        Texture logo = Assets.getInstance().getTexture(STD.LOGO);

        /*float x = context.getOriginX() + (context.getWidth() - logo.getWidth()) / 2;
        float y = context.getOriginY() + (context.getHeight() - logo.getHeight()) / 2;
        spriteBatch.draw(logo, x, y);*/



        tiledMapRenderer.setView(context.getCamera());

        tiledMapRenderer.render();
    }

    @Override
    public void onPositionChanged(float x, float y) {
        Gdx.app.log("pos", x + " " + y);
        context.getCamera().position.set(x,y,0);
    }
}
