package com.dgagamejam.throwobjects;

import com.badlogic.gdx.math.Rectangle;

public class VehicleObject extends ColliderObject {
	
	float maxVelocity = 50f;	//10's of px/sec
	float brake = 15f; 			//10's of px/sec/sec
	float acceleration = 12f; 	//10's of px/sec/sec
	float velocity = 0f; 		//10's of px/sec
	
	public VehicleObject(float x, float y, float scale, Rectangle collision, int level) {
		super(x, y, 0f, scale, collision, level);
	}
	
}