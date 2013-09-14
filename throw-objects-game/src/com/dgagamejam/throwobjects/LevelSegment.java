package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LevelSegment {

	float start, end;
	
	int index;
	
	ShapeRenderer sr = new ShapeRenderer();
	
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
		
	}
	
}
