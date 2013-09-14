package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ColliderObject extends GameObject {

	public ColliderObject(float x, float y, Rectangle collision) {
		super(x, y);
		
		this.collision = collision;
		// TODO Auto-generated constructor stub
	}

	Rectangle collision;
	
	int level;

	public Rectangle getCollision() {
		return collision;
	}

	public void setCollision(Rectangle collision) {
		this.collision = collision;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
