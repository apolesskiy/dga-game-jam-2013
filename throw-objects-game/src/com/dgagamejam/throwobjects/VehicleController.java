package com.dgagamejam.throwobjects;


public class VehicleController extends ColliderController {
	

	public VehicleObject getModel() {
		return (VehicleObject)model;
	}
	
	public void update(float dt, GameScreen screen) {
		super.update(dt, screen);
		
		model.rotation = 0f;
		
		model.x += ((VehicleObject)model).velocity * dt;
		
		//ascend to a higher level if we should/can
		if(getModel().y - getModel().collision.height/2f < getModel().level * Constants.LEVEL_HEIGHT) {
			model.rotation = 18.43f;
			float ascentSlope = Constants.LEVEL_HEIGHT/Constants.ASCENT_LENGTH;
			float dy = getModel().velocity * dt * ascentSlope;
			getModel().y += dy;
		}
		
		//if we are falling, set rotation
		if(getModel().y - getModel().collision.height/2f > getModel().level * Constants.LEVEL_HEIGHT) {
			model.rotation = (float)Math.toDegrees(Math.atan2(getModel().verticalVelocity, getModel().velocity));
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
		if(getModel().level >= Constants.LEVEL_COUNT || getModel().level<0) return;
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
		if(getModel().level >= Constants.LEVEL_COUNT || getModel().level<0) return;
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
		ProjectileObject rocket = new ProjectileObject(screen.player.model.getX(), screen.player.model.getY(), 0, 1);
		ProjectileController rocketc = new ProjectileController(rocket);
		screen.rockets.add(rocketc);
	}
}
