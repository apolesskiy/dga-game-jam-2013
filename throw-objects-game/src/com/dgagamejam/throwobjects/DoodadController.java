package com.dgagamejam.throwobjects;

public class DoodadController extends ColliderController {

	//TODO: return powerup on death
	
	public void onCollide() {
		//TODO: particle system
		DoodadObject mdl = (DoodadObject)model;
		mdl.destroyed = true;
	}
	
	
}
