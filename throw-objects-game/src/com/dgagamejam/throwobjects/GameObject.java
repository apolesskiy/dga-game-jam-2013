package com.dgagamejam.throwobjects;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

	float x, y, rotation, scale;
	
	public GameObject(float x, float y, float rotation, float scale) {
		this.x = x; this.y = y; this.rotation = rotation; this.scale = scale;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Vector2 getPosition() {
		return new Vector2(x,y);
	}
	
	public void setPosition(Vector2 v) {
		x = v.x;
		y = v.y;
	}
}
