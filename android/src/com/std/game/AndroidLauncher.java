package com.std.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.std.engine.ContextConfigurationBuilder;
import com.std.game.STD;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        ContextConfigurationBuilder builder = new ContextConfigurationBuilder()
                .setMinWidth(800)
                .setMinHeight(480)
                .setMaxWidth(854)
                .setMaxHeight(600)
                .setDebugCamera(false);

        initialize(new STD(builder.createContextConfiguration()), config);
    }
}
