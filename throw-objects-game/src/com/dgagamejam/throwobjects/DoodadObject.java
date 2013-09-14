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

class DoodadObjectView extends GameObjectView {

	public DoodadObjectView(GameController ctr) {
		super(ctr);
	}

	@Override
	public void update(float dt, GameScreen screen) {}

	@Override
	public void draw(float dt, SpriteBatch sb, GameScreen screen) {
		screen.shapeRenderer.begin(ShapeType.FilledRectangle);
		screen.shapeRenderer.setColor(Color.GREEN);
		DoodadObject veh = (DoodadObject)getModel();
		screen.shapeRenderer.filledRect(veh.x + veh.collision.x, veh.y+veh.collision.y, veh.collision.width, veh.collision.height);
		screen.shapeRenderer.end();
	}

}