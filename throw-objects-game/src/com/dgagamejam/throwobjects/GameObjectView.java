package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObjectView {

	public GameController controller;
	
	public GameObjectView(GameController ctr) {
		controller = ctr;
	}
	
	public GameObject getModel() {return controller.model; }
	
	public GameController getController() {return controller; }
	
	public abstract void update(float dt, GameScreen screen);

	public abstract void draw(float dt, SpriteBatch sb, GameScreen screen);

	
	
}
