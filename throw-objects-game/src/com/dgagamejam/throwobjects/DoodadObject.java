package com.dgagamejam.throwobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class DoodadObject extends ColliderObject {

	public DoodadObject(float x, float y, float rotation, float scale, Rectangle collision, int level) {
		super(x, y, rotation, scale, collision, level);
	}
}