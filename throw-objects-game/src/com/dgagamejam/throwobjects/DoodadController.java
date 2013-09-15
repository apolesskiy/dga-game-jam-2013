package com.dgagamejam.throwobjects;


public class DoodadController extends ColliderController {

	//TODO: return powerup on death
	
	public void update(float dt, GameScreen screen) {
		super.update(dt, screen);
		((DoodadObject)model).level = -1;
	}
	
	public void onCollide() {
		//TODO: particle system
		DoodadObject mdl = (DoodadObject)model;
		mdl.destroyed = true;
	}
}
