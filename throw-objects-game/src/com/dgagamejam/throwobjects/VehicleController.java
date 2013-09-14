package com.dgagamejam.throwobjects;


public class VehicleController extends ColliderController {
	

	public VehicleObject getModel() {
		return (VehicleObject)model;
	}
	
	public void update(float dt, GameScreen screen) {
		super.update(dt, screen);
		
		model.x += ((VehicleObject)model).velocity * dt;
		
		//ascend to a higher level if we should/can
		if(getModel().y - getModel().collision.height/2f < getModel().level * Constants.LEVEL_HEIGHT) {
			float ascentSlope = Constants.LEVEL_HEIGHT/Constants.ASCENT_LENGTH;
			float dy = getModel().velocity * dt * ascentSlope;
			getModel().y += dy;
		}
		
	}
	
	public void accelerate(float dt) {
		getModel().velocity += getModel().acceleration*dt;
		if(getModel().velocity > getModel().maxVelocity) getModel().velocity = getModel().maxVelocity;
	}
	
	public void brake(float dt) {
		getModel().velocity -= getModel().brake*dt;
		if(getModel().velocity < 0f) getModel().velocity = 0f;
	}
	
	public void levelUp(GameScreen screen) {
		//todo: check if in transition window
		if(getModel().level == Constants.LEVEL_COUNT) return;
		for(LevelTransition t : screen.levels[getModel().level].transitions) {
			if(t==null) continue;
			if(t.up && t.inEpsilon(getModel().x)) {
				getModel().level += 1;
				break;
			}
		}
	}
	
	public void levelDown(GameScreen screen) {
		//todo: check if in transition window
		if(getModel().level < 1) return;
		for(LevelTransition t : screen.levels[getModel().level].transitions) {
			if(t==null) continue;
			if(!t.up && t.inEpsilon(getModel().x)) {
				getModel().level -= 1;
				break;
			}
		}
	}
	
	public void fire(GameScreen screen) {
		//todo
	}
}
