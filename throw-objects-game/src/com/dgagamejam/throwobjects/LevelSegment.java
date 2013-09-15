package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LevelSegment {

	static String[] textureNames = new String[] { "Path1", "Path2", "Path3"};
	
	static String rampName = "ramp";
	
	static TextureRegion[] textures = new TextureRegion[textureNames.length];
	
	static TextureRegion ramp;
	
	float start, end;
	
	long drawSeed;
	
	public static boolean exists(int level, GameScreen screen) {
		return level > -1 && level < Constants.LEVEL_COUNT && 
				screen.levels[level] != null && screen.levels[level].end > screen.player.model.x - Constants.SCREEN_WIDTH/20; 
	}
	
	public static void initialize(GameScreen screen) {
		int ctr = 0;
		for(String s: textureNames) {
			textures[ctr] = screen.imageLibrary.findRegion(s);
			ctr++;
		}
		ramp = screen.imageLibrary.findRegion(rampName);
	}
	
	int index;
	
	LevelTransition[] transitions = new LevelTransition[10];
	
	int levelTransitionH = 0;
	
	public void addLevelTransition(float pos, boolean up) {
		if(transitions[levelTransitionH] == null)
			transitions[levelTransitionH] = new LevelTransition(pos, up);
		else
			transitions[levelTransitionH].reinit(pos, up);
		levelTransitionH = (levelTransitionH + 1) % transitions.length;
	}
	
	public LevelSegment( float s, int length, int level ) {
		start = s; end = start+length*Constants.LEVEL_HEIGHT; index = level;
		drawSeed = (long)s+length+System.currentTimeMillis();
	}
	
	public boolean isOnSegment(float x) {
		return x > start && x < end;
	}

	public void draw(float dt, SpriteBatch sb, GameScreen gameScreen) {
		int nTiles = (int)((end-start)/Constants.LEVEL_HEIGHT);
		java.util.Random pseudorandom = new java.util.Random(drawSeed);
		for(int i = 0; i<nTiles; i++) {
			int texIndex = pseudorandom.nextInt(textures.length);
			float x = start+i*Constants.LEVEL_HEIGHT;
			float y = (index-1)*Constants.LEVEL_HEIGHT;
			//if out of bounds, don't draw (but still generate pseudorandom)
			if(x > gameScreen.player.model.x+Constants.SCREEN_WIDTH/20+Constants.LEVEL_HEIGHT ||
			   x < gameScreen.player.model.x-Constants.SCREEN_WIDTH/20-Constants.LEVEL_HEIGHT ||
			   y > gameScreen.player.model.y+Constants.SCREEN_HEIGHT/20+Constants.LEVEL_HEIGHT ||
			   y < gameScreen.player.model.y-Constants.SCREEN_HEIGHT/20-Constants.LEVEL_HEIGHT)
				continue;
			sb.draw(textures[texIndex], x, y, 0, 0, Constants.LEVEL_HEIGHT, Constants.LEVEL_HEIGHT, 1f, 1f, 0f);
		}
		
		for(LevelTransition t : transitions) {
			if(t == null) continue;
			float x = t.pos;
			float y = (t.up ? index : index-1)*Constants.LEVEL_HEIGHT;
			sb.draw(ramp, x + (t.up ? 0 : Constants.ASCENT_LENGTH), y-1, 0, 0, Constants.ASCENT_LENGTH, Constants.LEVEL_HEIGHT, (t.up?1f:-1f), 1f, 0f);
		}
	}
}
