package com.dgagamejam.throwobjects.client;

import com.dgagamejam.throwobjects.ThrowObjectsGame;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(com.dgagamejam.throwobjects.Constants.SCREEN_WIDTH, com.dgagamejam.throwobjects.Constants.SCREEN_HEIGHT);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new ThrowObjectsGame();
	}
}