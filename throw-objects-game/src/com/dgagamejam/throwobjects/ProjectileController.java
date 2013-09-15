package com.dgagamejam.throwobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


public class ProjectileController extends GameController{
	
	public ProjectileController(ProjectileObject p){
		model = p;  
		view = new ProjectileView(this);
	}
	
	public ProjectileObject getModel() {
		return (ProjectileObject)model;
	}
	
	public ProjectileView getView() {
		return (ProjectileView)view;
	}
	
	public void update(float dt, GameScreen screen) {
		
		float mx = screen.player.model.x - Constants.SCREEN_WIDTH/20f + Gdx.input.getX()/10f;
		float my = screen.player.model.y + Constants.SCREEN_HEIGHT/20f - Gdx.input.getY()/10f;
		Vector2 mouse = new Vector2(mx,my);
		Vector2 toMouse = mouse.sub(model.getPosition());
		float toMouseAng = ((float)Math.toDegrees(Math.atan2(toMouse.y,toMouse.x))+360)%360;
		float diff = toMouseAng - model.rotation;
		if(Math.abs(diff) > 180) {
			diff = diff + (360 * Math.signum(diff) * -1f);
		}
		float deltaRotation = Math.min(Math.abs(diff),getModel().rotationRate * dt) * Math.signum(diff);
		
		model.rotation = model.rotation+deltaRotation;
		
		Vector2 deltaPos = new Vector2(getModel().velocity*dt, 0);
		deltaPos.rotate(model.rotation);
		model.x += deltaPos.x;
		model.y += deltaPos.y;
		
		if(getModel().counter == 300){
			 getModel().destroyed = true;
		}
		else{
			getModel().counter++;
		}
	}
}
