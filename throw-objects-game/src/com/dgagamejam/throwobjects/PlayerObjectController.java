package com.dgagamejam.throwobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerObjectController extends VehicleController {
	
	public void update(float dt, GameScreen screen) {
		super.update(dt, screen);
		
		//center camera on player
		screen.camera.position.set(getModel().x, getModel().y, screen.camera.position.z);
		
		//process user input
		//CONTROLS:
		//Shift -> accelerate
		//ctrl -> brake
		//w -> up
		//s -> down
		// todo - weapons (mouse click?)
		
		if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
			accelerate(dt);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			brake(dt);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			levelUp(screen);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			levelDown(screen);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			fire(screen);
		}
		
		
		//todo weapons
	
		
		//collide everything w player
		
		
		//move player object
	}
}
