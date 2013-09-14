package com.dgagamejam.throwobjects;

public class LevelSegmentGenerator {

	GameScreen screen;
	
	public LevelSegmentGenerator(GameScreen screen) {
		this.screen = screen;
	}
	
	
	public void generateStart() {
		
		int startLevel = screen.random.nextInt(Constants.LEVEL_COUNT-1);
		
		screen.levels[startLevel] = new LevelSegment(0f, (screen.random.nextInt(20)+40), startLevel);
		screen.levels[startLevel+1] = new LevelSegment(0f, 12, startLevel+1);
		
		screen.levels[startLevel].addLevelTransition(50, true);
		screen.levels[startLevel+1].addLevelTransition(120, false);
		
		DoodadFactory.createDoodad(screen, 50, (startLevel)*Constants.LEVEL_HEIGHT + 8);
		
		screen.player = (PlayerObjectController)VehicleFactory.createTank(screen, startLevel, 0f, true);
		
		((VehicleObject)screen.player.model).velocity = 20f;
		
	}
	
	public void update(float dt) {
		
		
		
		
	}
	
}
