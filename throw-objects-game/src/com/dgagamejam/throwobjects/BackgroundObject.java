package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BackgroundObject extends GameObject{
	
	float rotationRate = 90f;
	
	float rotation = 0f;
	
	float scale = 1f;
	
	public BackgroundObject(float x, float y) {
		super(x, y);
	}
	
	public GameObjectView createView(BackgroundController c) {
		return new BackgroundObjectView(c);
	}
}

class BackgroundObjectView extends GameObjectView {
	
	public static final String[] textureNames = new String[] {
		"Gear1", "Gear2", "Gear3"
	};
	
	String myTexture;
	
	public BackgroundObjectView(BackgroundController ctr) {
		super(ctr);
		myTexture = textureNames[new java.util.Random().nextInt(3)];
	}
	
	public BackgroundObject getModel() {
		return (BackgroundObject)controller.getModel();
	}
	
	public void draw (float dt, SpriteBatch sb, GameScreen screen) {
		TextureRegion tr = screen.imageLibrary.findRegion(myTexture);
		sb.draw(tr, getModel().x, getModel().y, tr.getRegionWidth()/20, tr.getRegionHeight()/20, tr.getRegionWidth()/10, tr.getRegionHeight()/10, getModel().scale, getModel().scale, getModel().rotation);
	}
	
	public void update(float dt, GameScreen screen) {
	}
	
}