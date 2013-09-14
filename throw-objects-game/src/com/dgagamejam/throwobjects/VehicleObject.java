package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class VehicleObject extends ColliderObject {
	
	float maxVelocity = 36f;	//10's of px/sec
	float brake = 3f; 			//10's of px/sec/sec
	float acceleration = 12f; 	//10's of px/sec/sec
	float velocity = 0f; 		//10's of px/sec
	
	public VehicleObject(float x, float y, float scale, Rectangle collision, int level) {
		super(x, y, 0f, scale, collision, level);
	}
	
}