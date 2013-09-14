package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundController extends GameController{
	
	public BackgroundController(BackgroundObject o) {
		model = o;
		view = (BackgroundObjectView) o.createView(this);
	}
	
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
}
