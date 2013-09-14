package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class VehicleFactory {

	public static final float TANK_IMAGE_SCALE = 0.5f;
	
	public static Rectangle TANK_COLLISION = new Rectangle(-8f,-6f,15,10);
	
	static {
		
		TANK_COLLISION.set(TANK_COLLISION.x*TANK_IMAGE_SCALE, TANK_COLLISION.y*TANK_IMAGE_SCALE, 
				TANK_COLLISION.width*TANK_IMAGE_SCALE, TANK_COLLISION.height*TANK_IMAGE_SCALE);
		
	}
	
	public static final String[] textureNames = new String[] {
		"tank"
	};
	
	public static final TextureRegion[] textures = new TextureRegion[textureNames.length]; 
	
	public static void initialize(GameScreen screen) {
		int ctr = 0;
		for(String s: textureNames) {
			textures[ctr] = screen.imageLibrary.findRegion(s);
			ctr++;
		}
	}
	
	public static VehicleController createTank(GameScreen screen, int level, float x, boolean player) {
		
		VehicleController gear = (player ? new PlayerObjectController() : new VehicleController());
		VehicleObject g = new VehicleObject(x, level * Constants.LEVEL_HEIGHT + TANK_COLLISION.height/2f, TANK_IMAGE_SCALE, TANK_COLLISION, level);
		TextureView tv = new TextureView(gear, textures[0]);
		
		gear.model = g;
		gear.view = tv;
		tv.controller = gear;
		
		//todo: add to scene
		
		return gear;
	}
	
}
