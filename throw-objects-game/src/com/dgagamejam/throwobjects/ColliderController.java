package com.dgagamejam.throwobjects;

public class ColliderController extends GameController {

	public ColliderObject getModel() {
		return (ColliderObject)model;
	}
	
	public void update(float dt, GameScreen screen) {
		super.update(dt, screen);
		
		//check if we are dead
		if(getModel().hp <= 0) {
			getModel().destroyed = true;
			onDestroy();
			return;
		}
		
		//gravity things
		//check if we are on our level - if we are above it, accelerate down
		//1. check that our level exists. If not, start falling
		LevelSegment currentLevel = (getModel().level >= 0 && getModel().level < 20 ? screen.levels[getModel().level] : null);
		int lowest = getModel().level;
		while(lowest > -1 && (currentLevel == null || 
				(!currentLevel.isOnSegment(getModel().x) && !currentLevel.isOnSegment(getModel().x + Constants.ASCENT_LENGTH)))) {
			//find the lowest available level
			lowest--;
			currentLevel = (lowest >= 0 ? screen.levels[lowest] : null);
		}
		getModel().level = lowest;
		//if we are more than 1 level's worth above our level, check above levels for landing.
		if(model.y - Constants.LEVEL_HEIGHT > Constants.LEVEL_HEIGHT * getModel().level) {
			int highest = (int)(model.y / Constants.LEVEL_HEIGHT)-1;
			while(highest > -1 && highest > getModel().level && (screen.levels[highest] == null || !screen.levels[highest].isOnSegment(model.x))) {
				highest--;
			}
			getModel().level = highest;
		}
		if(getModel().level < 0) {
			getModel().level = -999;
		}
		
		boolean descending = false;
		
		//if we are above our level, fall down
		if(getModel().y - getModel().collision.height/2f > getModel().level * Constants.LEVEL_HEIGHT) {
			getModel().verticalVelocity += Constants.GRAVITY * dt;
			descending = true;
		}
		
		getModel().y += getModel().verticalVelocity * dt;
		
		if(descending && getModel().y - getModel().collision.height/2f < getModel().level * Constants.LEVEL_HEIGHT) {
			getModel().y = getModel().level * Constants.LEVEL_HEIGHT + getModel().collision.height/2f;
			//take fall damage if velocity is high enough
			if(getModel().verticalVelocity > getModel().fallDamageThreshold) {
				getModel().hp -= (int)(getModel().verticalVelocity - getModel().fallDamageThreshold);
			}
			getModel().verticalVelocity = 0;

		}
	}
	
	public void onDestroy() {}
	
}
