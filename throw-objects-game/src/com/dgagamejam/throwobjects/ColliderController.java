package com.dgagamejam.throwobjects;

public class ColliderController extends GameController {

	public ColliderObject getModel() {
		return (ColliderObject)model;
	}
	
	public void update(float dt, GameScreen screen) {
		super.update(dt, screen);
		//gravity things
		//check if we are on our level - if we are above it, accelerate down
		//1. check that our level exists. If not, decrement by 1.
		LevelSegment currentLevel = (getModel().level >= 0 ? screen.levels[getModel().level] : null);
		if(currentLevel == null || !currentLevel.isOnSegment(getModel().x)) {
			getModel().level--;
		}
		
		boolean descending = false;
		
		//if we are above our level, fall down
		if(getModel().y > getModel().level * Constants.LEVEL_HEIGHT) {
			getModel().verticalVelocity += Constants.GRAVITY * dt;
			descending = true;
		}
		
		getModel().y += getModel().verticalVelocity * dt;
		
		if(descending && getModel().y < getModel().level * Constants.LEVEL_HEIGHT) {
			getModel().y = getModel().level * Constants.LEVEL_HEIGHT;
			getModel().verticalVelocity = 0;
		}
	}
	
}
