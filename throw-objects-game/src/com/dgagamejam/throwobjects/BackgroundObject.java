package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BackgroundObject extends GameObject{
	
	public BackgroundObject(float x, float y) {
		super(x, y);
	}
	
	public GameObjectView createView(BackgroundController c) {
		return new BackgroundObjectView(c);
	}
}

class BackgroundObjectView extends GameObjectView{
	
	public BackgroundObjectView(BackgroundController ctr) {
		super(ctr);
	}
	
	ShapeRenderer sr = new ShapeRenderer();
	
	public void draw (float dt, SpriteBatch sb, GameScreen screen) {
		
		screen.shapeRenderer.begin(ShapeRenderer.ShapeType.FilledRectangle);
		BackgroundObject bg = (BackgroundObject)getModel();
		screen.shapeRenderer.setColor(Color.RED);
		screen.shapeRenderer.filledRect(bg.x, bg.y, 10, 10);
		screen.shapeRenderer.end();
	}
	
	public void update(float dt, GameScreen screen) {
	}
	
}