package com.std.game;

import com.badlogic.gdx.Gdx;
import com.std.engine.Context;
import com.std.engine.ContextConfiguration;
import com.std.engine.gamestate.transition.FadeIn;
import com.std.engine.gamestate.transition.FadeOut;
import com.std.game.states.LoadingResources;
import com.std.game.states.PlayGameState;

public class STD extends Context {

	public static final String TITLE = "Sexual Transmission Diseases";

	/**
	 * Assets
	 */
	public static final String ATLAS_NAME = "spacestorm.atlas";
	public static final String LOGO = "logo.png";
	public static final String VENDETTA_VERA_MP3 = "vendetta_vera.mp3";
	public static final String TRINITA_MP3 = "trinita.mp3";
	public static final String GESU_3_MP3 = "gesu_3.mp3";

	public static final int FONT_SIZE_32 = 32;

	public static final String FONT_FILE_NAME = "fipps-regular.ttf";
	public static final String PLAY_GAME_STATE_FONT = "ssfont.ttf";

	public static final String TEST_MAP = "maptest.tmx";

	public LoadingResources loadingResources;
	public PlayGameState playGameState;

	/**
	 * @param contextConfiguration contiene tutte le informazini necessarie
	 *                             per configurare la camera
	 */
	public STD(ContextConfiguration contextConfiguration) {
		super(contextConfiguration);
	}

	@Override
	public void create () {
		super.create();
		Gdx.input.setCatchBackKey(true);
		loadingResources = new LoadingResources(this);
		playGameState = new PlayGameState(this);
		getGameStateManager().addGameState(loadingResources);
		getGameStateManager().addGameState(playGameState);
		getGameStateManager().enterGameState(LoadingResources.ID,
				new FadeIn(this),
				new FadeOut(this));
	}
}
