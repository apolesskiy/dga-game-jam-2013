package com.dgagamejam.throwobjects;

import com.badlogic.gdx.math.Rectangle;

public abstract class ColliderObject extends GameObject {

	public ColliderObject(float x, float y, Rectangle collision, int level) {
		super(x, y);
		
		this.collision = collision;
		this.level = level;
		// TODO Auto-generated constructor stub
	}

	Rectangle collision;
	
	int level;
	float verticalVelocity = 0f;

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
