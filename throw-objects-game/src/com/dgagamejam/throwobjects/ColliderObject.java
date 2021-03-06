package com.dgagamejam.throwobjects;

import com.badlogic.gdx.math.Rectangle;

public class ColliderObject extends GameObject {
	
	public ColliderObject(float x, float y, float rotation, float scale, Rectangle collision, int level) {
		super(x, y, rotation, scale);
		
		this.collision = collision;
		this.level = level;
		// TODO Auto-generated constructor stub
	}

	Rectangle collision;
	
	int level;
	float verticalVelocity = 0f;
	float fallDamageThreshold = 30;
	int hp = 100;

	boolean destroyed = false;
	
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
	
	public int getHp(){
		return hp;
	}
	
	public boolean collides (ColliderObject other) {
		
		Rectangle col = new Rectangle(collision);
		Rectangle othercol = new Rectangle(other.collision);
		col.x+=x;
		col.y+=y;
		othercol.x+=other.x;
		othercol.y+=other.y;
		return col.overlaps(othercol) && !destroyed &&!other.destroyed;
	}
	
}
