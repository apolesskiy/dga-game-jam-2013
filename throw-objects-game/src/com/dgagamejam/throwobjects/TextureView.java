package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureView extends GameObjectView {

	public TextureView(GameController ctr, TextureRegion t) {
		super(ctr);
		this.texture = t;
	}

	TextureRegion texture;

	@Override
	public void update(float dt, GameScreen screen) {}

	@Override
	public void draw(float dt, SpriteBatch sb, GameScreen screen) {
		sb.draw(texture, getModel().x, getModel().y, texture.getRegionWidth()/20, texture.getRegionHeight()/20, 
				texture.getRegionWidth()/10, texture.getRegionHeight()/10, getModel().scale, getModel().scale, getModel().rotation);
	}
	
}
