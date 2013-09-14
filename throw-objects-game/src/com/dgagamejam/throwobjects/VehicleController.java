package com.dgagamejam.throwobjects;

public class VehicleController extends GameController {
	
	public VehicleController(VehicleObject o) {
		model = o;
		view = (VehicleObjectView) o.createView(this);
	}
	
	public void update(float dt, GameScreen screen) {
		super.update(dt, screen);
		
		model.x += ((VehicleObject)model).velocity * dt;
		
	}
	
}
