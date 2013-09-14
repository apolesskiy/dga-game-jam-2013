package com.dgagamejam.throwobjects;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {

	Random random = new Random();
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	public PlayerObjectController player;
	
	Game game;
	
	//doodad list
	
	//player projectile list
	
	//enemy/object list
	
	//enemy projectile list
	
	LevelSegment[] levels;

	public ShapeRenderer shapeRenderer;
	
	public GameScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		
		//setup
		batch = new SpriteBatch();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		
		//
		shapeRenderer = new ShapeRenderer();
		
		//level and objects init
		levels = new LevelSegment[Constants.LEVEL_COUNT];
		
		//int startLevel = random.nextInt(Constants.LEVEL_COUNT);
		
		int startLevel = 1;
		
		levels[startLevel] = new LevelSegment(0f, (float)(random.nextInt(2000)+2000), startLevel);
		
		VehicleObject playercar = new VehicleObject(10f, Constants.LEVEL_HEIGHT*startLevel+1f, new Rectangle(-30,0,60,20));
		
		playercar.setLevel(startLevel);
		
		player = new PlayerObjectController(playercar);
		
		((VehicleObject)player.model).velocity = 20f;
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}
	
	public void update(float dt) {
		
		//update player
		player.update(dt, this);
		
	}
	
	public void draw(float dt) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		//draw everything (in order!)
		
		//draw levels + transitions
		for(int i = Constants.LEVEL_COUNT-1; i>=0; i--) {
			if(levels[i] != null) {
				levels[i].draw(dt, batch, this);
			}

		}
		
		//draw player
		player.draw(dt, batch, this);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {}


	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		//dealloc resources
	}

}
