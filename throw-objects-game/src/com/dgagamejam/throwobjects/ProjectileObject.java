package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ProjectileObject extends GameObject{

	float rotationRate = 180f;
	float velocity = 57f;
	int counter = 0;
	public boolean destroyed = false;
	
	public ProjectileObject(float x, float y, float rotation, float scale) {
		super(x, y, rotation, scale);
	}

}

class ProjectileView extends GameObjectView {
	
	public ProjectileView (ProjectileController ctr) {
		 super(ctr);
	}
	
	public void update(float dt, GameScreen screen){
		
	}

	public void draw(float dt, SpriteBatch sb, GameScreen screen){
		TextureRegion tr = screen.imageLibrary.findRegion("rocket3");
		sb.draw(tr, getModel().x - tr.getRegionWidth()/20, getModel().y - tr.getRegionHeight()/20, 
				tr.getRegionWidth()/20, tr.getRegionHeight()/20, 
				tr.getRegionWidth()/10, tr.getRegionHeight()/10, getModel().scale, getModel().scale, getModel().rotation);
	}
	
}
