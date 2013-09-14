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
			//take fall damage if velocity is high enough
			if(getModel().verticalVelocity > getModel().fallDamageThreshold) {
				getModel().hp -= (int)(getModel().verticalVelocity - getModel().fallDamageThreshold);
			}
			getModel().verticalVelocity = 0;

		}
	}
	
	public void onDestroy() {}
	
}
