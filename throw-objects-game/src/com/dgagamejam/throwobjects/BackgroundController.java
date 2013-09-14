package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundController extends GameController{
	
	public BackgroundController(BackgroundObject o) {
		model = o;
		view = (BackgroundObjectView) o.createView(this);
	}
	
	public BackgroundObject getModel() {
		return (BackgroundObject)model;
	}
	
	public void update(float dt, GameScreen screen) {
		super.update(dt, screen);
		
		getModel().rotation += getModel().rotationRate * dt;
	}
}
