package com.dgagamejam.throwobjects;


public class LevelTransition {

	float pos;
	
	boolean up;
	
	public LevelTransition(float pos, boolean up) {
		this.pos = pos;
		this.up = up;
	}
	
	public void reinit(float pos, boolean up) {
		this.pos = pos;
		this.up = up;
	}
	
	public boolean inEpsilon(float x) {
		return Math.abs(x - pos) < Constants.TRANSITION_EPSILON;
	}
}
