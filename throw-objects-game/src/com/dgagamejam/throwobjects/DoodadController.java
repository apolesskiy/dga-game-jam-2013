package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DoodadController extends ColliderController {

	//TODO: return powerup on death
	
	public void onCollide() {
		//TODO: particle system
		DoodadObject mdl = (DoodadObject)model;
		mdl.destroyed = true;
	}
}
