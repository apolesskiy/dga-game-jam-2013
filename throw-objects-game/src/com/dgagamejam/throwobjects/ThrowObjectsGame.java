package com.dgagamejam.throwobjects;

import com.badlogic.gdx.Game;

public class ThrowObjectsGame extends Game {

	GameScreen screen;
	
	public void create() {		
		
		screen = new GameScreen(this);
		
		setScreen(screen);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
