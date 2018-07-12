package com.std.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.std.engine.ContextConfigurationBuilder;
import com.std.game.STD;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		ContextConfigurationBuilder builder = new ContextConfigurationBuilder()
				.setMinWidth(800)
				.setMinHeight(480)
				.setMaxWidth(854)
				.setMaxHeight(600)
				.setDebugCamera(false);
		new LwjglApplication(new STD(builder.createContextConfiguration()), config);
	}
}
