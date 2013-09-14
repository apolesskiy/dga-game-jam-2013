package com.dgagamejam.throwobjects;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

	TextureAtlas imageLibrary;
	
	
	
	
	TextureRegion bg;
	float bgX;
	float bgY;
	float width;
	float height;
	
	@Override
	public void show() {
		
		//setup
		batch = new SpriteBatch();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w/10, h/10);
		
		imageLibrary = new TextureAtlas("data/images/img.pack");
		
		//
		shapeRenderer = new ShapeRenderer();		
		
		bgGears = new HashSet<BackgroundController>(20);
		spawnGear = true;	
		
		//level and objects init
		levels = new LevelSegment[Constants.LEVEL_COUNT];
		
		int startLevel = random.nextInt(Constants.LEVEL_COUNT-1);
		
		levels[startLevel] = new LevelSegment(0f, (float)(random.nextInt(200)+200), startLevel);
		levels[startLevel+1] = new LevelSegment(0f, (float)(100), startLevel+1);
		
		levels[startLevel].transitions.add(new LevelTransition(50, true));
		levels[startLevel+1].transitions.add(new LevelTransition(100, false));
		
		VehicleObject playercar = new VehicleObject(10f, Constants.LEVEL_HEIGHT*startLevel+1f, new Rectangle(-3,0,6,2), startLevel);
		
		player = new PlayerObjectController(playercar);
		
		((VehicleObject)player.model).velocity = 2f;
		
		bg = this.imageLibrary.findRegion("Gear1");
		width = bg.getRegionWidth()/4;
		height = bg.getRegionHeight()/4;
		bgX = player.model.getX() - (width/2);
		bgY = player.model.getY() - (height/2);
		
		
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}
	
	public void update(float dt) {
		
		//update player
		player.update(dt, this);
		
		//spawn gears
		if(spawnGear && random.nextFloat()>0.90f){
			BackgroundObject g = new BackgroundObject(player.model.x + 80f, random.nextInt((int)(Constants.LEVEL_COUNT * Constants.LEVEL_HEIGHT)));
			BackgroundController gear = new BackgroundController(g);
			gear.getModel().rotationRate = 90-random.nextInt(180);
			gear.getModel().scale = random.nextFloat() + 0.5f;
			bgGears.add(gear);
		}
		
		Iterator<BackgroundController> iter = bgGears.iterator();
		BackgroundController bgo;
		while(iter.hasNext()) {
			bgo = iter.next();
			if(player.model.x - bgo.model.x > 1000) {
				iter.remove();
			} else {
				bgo.update(dt, this);
			}
		}
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
			
			//draw player
			player.draw(dt, batch, this);
			
			
			//if((bgX - player.model.getX()) > ){
				//bgx += width;
			//}
			
			
			//draw background
			batch.draw(bg, bgX, bgY, width, height);
			batch.draw(bg, bgX + width, bgY, width, height);
			batch.draw(bg, bgX - width, bgY, width, height);
			batch.draw(bg, bgX, bgY + height, width, height);
			batch.draw(bg, bgX, bgY - height, width, height);
			batch.draw(bg, bgX + width, bgY + height, width, height);
			batch.draw(bg, bgX - width, bgY - height, width, height);
			batch.draw(bg, bgX + width, bgY - height, width, height);
			batch.draw(bg, bgX - width, bgY + height, width, height);


			
			
			for(BackgroundController bc : bgGears){
				bc.draw(dt, batch, this);
			}	
			
			//draw levels + transitions
			for(int i = Constants.LEVEL_COUNT-1; i>=0; i--) {
				if(levels[i] != null) {
					levels[i].draw(dt, batch, this);
				}
	
			}
			
			shapeRenderer.begin(ShapeType.Circle); 	
			{
				shapeRenderer.setColor(Color.GREEN);
				shapeRenderer.circle(500, 0, 100);
			}	
			shapeRenderer.end();
		}
		
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
