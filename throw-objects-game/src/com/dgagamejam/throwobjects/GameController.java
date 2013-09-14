package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameController {

	GameObject model;
	GameObjectView view;
	
	public void update(float dt, GameScreen screen) {
		if(view != null) {
			view.update(dt, screen);
		}
	}
	
	public void draw(float dt, SpriteBatch sb, GameScreen screen) {
		if(view != null) {
			view.draw(dt, sb, screen);
		}
	}
	
	public GameObject getModel() { return model; }
	
	public GameObjectView getView() { return view; }
}
