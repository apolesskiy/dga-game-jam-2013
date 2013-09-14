package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackgroundFactory {

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
	
	public static BackgroundController createBackgroundGear(GameScreen screen, float x, float y, float scale) {
		
		BackgroundController gear = new BackgroundController();
		BackgroundObject g = new BackgroundObject(x,y,0f,scale);
		TextureView tv = new TextureView(gear, textures[screen.random.nextInt(textures.length)]);
		
		gear.model = g;
		gear.view = tv;
		
		gear.getModel().rotationRate = 60-screen.random.nextInt(120);
		screen.bgGears.add(gear);
		
		return gear;
	}
	
}
