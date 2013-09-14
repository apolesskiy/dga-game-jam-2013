package com.dgagamejam.throwobjects;


public class BackgroundController extends GameController{
	
	public BackgroundObject getModel() {
		return (BackgroundObject)model;
	}
	
	public void update(float dt, GameScreen screen) {
		super.update(dt, screen);
		
		getModel().rotation += getModel().rotationRate * dt;
	}
}
