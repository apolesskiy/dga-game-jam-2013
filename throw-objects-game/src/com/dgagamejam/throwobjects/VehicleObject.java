package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class VehicleObject extends ColliderObject {
	
	float maxVelocity = 30f;	//10's of px/sec
	float brake = 3f; 			//10's of px/sec/sec
	float acceleration = 5f; 	//10's of px/sec/sec
	float velocity = 0f; 		//10's of px/sec
	
	public VehicleObject(float x, float y, Rectangle collision, int level) {
		super(x, y, collision, level);
	}
	
	public GameObjectView createView(VehicleController c) {
		return new VehicleObjectView(c);
	}
	
}

class VehicleObjectView extends GameObjectView {
	
	public VehicleObjectView(VehicleController ctr) {
		super(ctr);
	}
	
	ShapeRenderer sr = new ShapeRenderer();
	
	public void draw (float dt, SpriteBatch sb, GameScreen screen) {
		
		screen.shapeRenderer.begin(ShapeRenderer.ShapeType.FilledRectangle);
		VehicleObject veh = (VehicleObject)getModel();
		screen.shapeRenderer.setColor(Color.BLUE);
		screen.shapeRenderer.filledRect(veh.x + veh.collision.x, veh.y+veh.collision.y, veh.collision.width, veh.collision.height);
		screen.shapeRenderer.end();
	}

	@Override
	public void update(float dt, GameScreen screen) {
	}
	
}