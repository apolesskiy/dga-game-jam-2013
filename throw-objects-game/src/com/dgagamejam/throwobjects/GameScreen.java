package com.dgagamejam.throwobjects;

import java.util.HashSet;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {

	Random random = new Random();
	
	OrthographicCamera camera;
	SpriteBatch batch;
	
	public PlayerObjectController player;
	
	Game game;
	
	//doodad list
	
	//player projectile list
	
	//enemy/object list
	
	//enemy projectile list
	
	LevelSegment[] levels;

	HashSet<LevelTransition> levelTransitions = new HashSet<LevelTransition>(20);	
	
	HashSet<BackgroundController> bgGears;
	boolean spawnGear;

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
		camera = new OrthographicCamera(w/10, h/10);
		
		//
		shapeRenderer = new ShapeRenderer();		
		
		bgGears = new HashSet<BackgroundController>(20);
		spawnGear = false;	
		
		//level and objects init
		levels = new LevelSegment[Constants.LEVEL_COUNT];
		
		int startLevel = random.nextInt(Constants.LEVEL_COUNT);
		
		levels[startLevel] = new LevelSegment(0f, (float)(random.nextInt(200)+200), startLevel);
		levels[startLevel+1] = new LevelSegment(0f, (float)(100), startLevel+1);
		
		levels[startLevel].transitions.add(new LevelTransition(50, true));
		levels[startLevel+1].transitions.add(new LevelTransition(100, false));
		
		VehicleObject playercar = new VehicleObject(10f, Constants.LEVEL_HEIGHT*startLevel+1f, new Rectangle(-3,0,6,2), startLevel);
		
		player = new PlayerObjectController(playercar);
		
		((VehicleObject)player.model).velocity = 2f;
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
		camera.update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		batch.begin();
		{
			//draw everything (in order!)
			
			//draw levels + transitions
			for(int i = Constants.LEVEL_COUNT-1; i>=0; i--) {
				if(levels[i] != null) {
					levels[i].draw(dt, batch, this);
				}
	
			}
			
			//draw player
			player.draw(dt, batch, this);
			
			shapeRenderer.begin(ShapeType.Circle); 	
			{
				shapeRenderer.setColor(Color.GREEN);
				shapeRenderer.circle(500, 0, 100);
			}	
			shapeRenderer.end();
		}
		
		//spawn gears
		if(spawnGear){
			BackgroundObject g = new BackgroundObject(random.nextInt(Constants.SCREEN_WIDTH), random.nextInt(Constants.SCREEN_HEIGHT));
			BackgroundController gear = new BackgroundController(g);
			bgGears.add(gear);
		}
		
		for(BackgroundController bc : bgGears){
			bc.draw(dt, batch, this);
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
