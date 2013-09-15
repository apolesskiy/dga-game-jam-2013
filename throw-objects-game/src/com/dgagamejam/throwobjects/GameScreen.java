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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameScreen implements Screen {

	Random random = new Random();
	
	OrthographicCamera camera;
	SpriteBatch batch;
	
	public PlayerObjectController player;
	
	Game game;
	
	//player projectile list
	
	//enemy/object list
	
	//enemy projectile list
	
	LevelSegment[] levels;
	LevelSegmentGenerator levelGen;
	
	HashSet<BackgroundController> bgGears;

	HashSet<DoodadController> doodads;
	
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
		//audio
		AudioManager audioManager = new AudioManager();
		audioManager.play("data/77738__timbre__cinematic-ticking-clock-heavy-gears.wav");
		//initialize factories
		BackgroundFactory.initialize(this);
		DoodadFactory.initialize(this);
		VehicleFactory.initialize(this);
		LevelSegment.initialize(this);
		
		//
		levelGen = new LevelSegmentGenerator(this);
		
		shapeRenderer = new ShapeRenderer();		
		
		bgGears = new HashSet<BackgroundController>(20);
		doodads = new HashSet<DoodadController>(20);	
		
		//level and objects init
		levels = new LevelSegment[Constants.LEVEL_COUNT];
		
		levelGen.generateStart();
		

		
		bg = this.imageLibrary.findRegion("background");
		width = bg.getRegionWidth()/10;
		height = bg.getRegionHeight()/10;
		bgX = player.model.getX() - (width/2);
		bgY = player.model.getY() - (height/2);
		
		
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}
	
	public void update(float dt) {
		
		levelGen.update(dt);
		
		//update player
		player.update(dt, this);
		
		//spawn gears
		{
			float spawnChance = 0.05f * player.getModel().velocity * dt;
			if(random.nextFloat() < spawnChance) {
				float spawnScale = ((float)random.nextInt(8)) / 2f;
				float spawnY = player.getModel().y + (random.nextInt(Constants.SCREEN_HEIGHT) - Constants.SCREEN_HEIGHT / 2) / 10f;
				float spawnX = player.getModel().x + 80;
				BackgroundFactory.createBackgroundGear(this, spawnX, spawnY, spawnScale);
				
			}
		}

		//spawn doodads
		{
			float spawnChance = 0.1f * player.getModel().velocity * dt;
			if(random.nextFloat() < spawnChance) {
				float spawnY = player.getModel().y + Constants.SCREEN_HEIGHT/20;
				float spawnX = player.getModel().x + random.nextInt(Constants.SCREEN_WIDTH/20);
				DoodadFactory.createDoodad(this, spawnX, spawnY);
				
			}
		}
		
		//update gears
		{
			Iterator<BackgroundController> iter = bgGears.iterator();
			BackgroundController bgo;
			while(iter.hasNext()) {
				bgo = iter.next();
				if(player.model.x - bgo.model.x > Constants.OBJECT_DESPAWN_DISTANCE) {
					iter.remove();
				} else {
					bgo.update(dt, this);
				}
			}
		}
		
		//update doodads (NOT collision)
		{
			Iterator<DoodadController> iter = doodads.iterator();
			DoodadController d;
			while(iter.hasNext()) {
				d = iter.next();
				if(player.model.x - d.model.x > Constants.SCREEN_WIDTH/20) {
					iter.remove();
				} else {
					d.update(dt, this);
				}
			}
		}
		
		
		//update background
		if((player.model.getX() - bgX) > width){
			bgX += width;
		}
		if((player.model.getY() - bgY) > (height)){
			bgY += height;
		}
		else if((bgY - player.model.getY()) > (height)){
			bgY -= height;
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
			
			
			//draw background
			batch.draw(bg, bgX, bgY, width, height);
			batch.draw(bg, bgX + width - 0.1f, bgY, width, height);
			batch.draw(bg, bgX - width + 0.1f, bgY, width, height);
			batch.draw(bg, bgX, bgY + height - 0.1f, width, height);
			batch.draw(bg, bgX, bgY - height + 0.1f, width, height);
			batch.draw(bg, bgX + width - 0.1f, bgY + height - 0.1f, width, height);
			batch.draw(bg, bgX - width + 0.1f, bgY - height + 0.1f, width, height);
			batch.draw(bg, bgX + width - 0.1f, bgY - height + 0.1f, width, height);
			batch.draw(bg, bgX - width + 0.1f, bgY + height - 0.1f, width, height);	
			batch.draw(bg, bgX, bgY + 2 * height - 0.1f, width, height);
			batch.draw(bg, bgX, bgY - 2 * height + 0.1f, width, height);
			batch.draw(bg, bgX + width - 0.1f, bgY + 2 * height - 0.1f, width, height);
			batch.draw(bg, bgX + width - 0.1f, bgY - 2 * height + 0.1f, width, height);
			batch.draw(bg, bgX - width + 0.1f, bgY + 2 * height - 0.1f, width, height);
			batch.draw(bg, bgX - width + 0.1f, bgY - 2 * height + 0.1f, width, height);
			
			
			for(BackgroundController bc : bgGears){
				bc.draw(dt, batch, this);
			}	
			
			//draw levels + transitions
			for(int i = Constants.LEVEL_COUNT-1; i>=0; i--) {
				if(levels[i] != null) {
					levels[i].draw(dt, batch, this);
				}
	
			}			
			
			//draw doodads
			for(DoodadController d: doodads) {
				d.draw(dt, batch, this);
			}
			
			//draw player
			player.draw(dt, batch, this);
			
			
			//draw hp bar
			shapeRenderer.begin(ShapeRenderer.ShapeType.FilledRectangle);
			shapeRenderer.setColor(Color.WHITE);
			shapeRenderer.filledRect(player.model.getX()-(Constants.SCREEN_WIDTH/21.5f), player.model.getY()+(Constants.SCREEN_HEIGHT/25f), 20f, 3f);
			shapeRenderer.setColor(Color.RED);
			shapeRenderer.filledRect(player.model.getX()-(Constants.SCREEN_WIDTH/21.5f), player.model.getY()+(Constants.SCREEN_HEIGHT/25f), 20f*((ColliderObject)player.model).getHp()/100, 3f);
			shapeRenderer.end();
			
			
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
