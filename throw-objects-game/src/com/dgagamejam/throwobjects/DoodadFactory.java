package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class DoodadFactory {
	
	public static float DOODAD_IMAGE_SCALE = 0.5f;
	
	public static Rectangle DOODAD_COLLISION = new Rectangle(-6f, -6f, 12, 12);
	
	static {
		
		DOODAD_COLLISION.set(DOODAD_COLLISION.x*DOODAD_IMAGE_SCALE, DOODAD_COLLISION.y*DOODAD_IMAGE_SCALE, 
				DOODAD_COLLISION.width*DOODAD_IMAGE_SCALE, DOODAD_COLLISION.height*DOODAD_IMAGE_SCALE);
		
	}
	
	public static final String[] textureNames = new String[] {
		"Gear1", "Gear2", "Gear3"
	};
	
	public static final TextureRegion[] textures = new TextureRegion[textureNames.length]; 
	
	public static void initialize(GameScreen screen) {
		int ctr = 0;
		for(String s: textureNames) {
			textures[ctr] = screen.imageLibrary.findRegion(s);
			ctr++;
		}
	}
	
	public static DoodadController createDoodad(GameScreen screen, float x, float y) {
		
		DoodadController gear = new DoodadController();
		DoodadObject g = new DoodadObject(x, y, 0f, DOODAD_IMAGE_SCALE, DOODAD_COLLISION, (int)(y/Constants.LEVEL_HEIGHT));
		TextureView tv = new TextureView(gear, textures[screen.random.nextInt(textures.length)]);
		
		gear.model = g;
		gear.view = tv;
		tv.controller = gear;
		g.verticalVelocity = -20f;
		
		screen.doodads.add(gear);
		
		return gear;
	}
}
