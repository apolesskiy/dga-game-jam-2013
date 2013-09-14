package com.dgagamejam.throwobjects;

import java.util.HashSet;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LevelSegment {

	float start, end;
	
	int index;
	
	HashSet<LevelTransition> transitions = new HashSet<LevelTransition>();
	
	public LevelSegment( float s, float e, int l ) {
		start = s; end = e; index = l;
	}
	
	public boolean isOnSegment(float x) {
		return x > start && x < end;
	}

	public void draw(float dt, SpriteBatch sb, GameScreen gameScreen) {
		gameScreen.shapeRenderer.begin(ShapeType.Line);
		gameScreen.shapeRenderer.setColor(Color.RED);
		gameScreen.shapeRenderer.line(start, index * Constants.LEVEL_HEIGHT, end, index * Constants.LEVEL_HEIGHT);
		gameScreen.shapeRenderer.end();
		
		for(LevelTransition t : transitions) {
			gameScreen.shapeRenderer.begin(ShapeType.Line);
			gameScreen.shapeRenderer.setColor(Color.RED);
			gameScreen.shapeRenderer.line(t.pos, index * Constants.LEVEL_HEIGHT, t.pos + Constants.ASCENT_LENGTH, (t.up ? index+1 : index-1) * Constants.LEVEL_HEIGHT);
			gameScreen.shapeRenderer.end();
		}
	}
}
