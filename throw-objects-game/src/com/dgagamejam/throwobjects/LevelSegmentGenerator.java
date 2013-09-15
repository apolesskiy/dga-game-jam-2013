package com.dgagamejam.throwobjects;

public class LevelSegmentGenerator {

	GameScreen screen;
	
	public LevelSegmentGenerator(GameScreen screen) {
		this.screen = screen;
	}
	
	
	public void generateStart() {
		
		int startLevel = screen.random.nextInt(Constants.LEVEL_COUNT-1);
		
		screen.levels[startLevel] = new LevelSegment(0f, (screen.random.nextInt(20)+40), startLevel);
		
		screen.player = (PlayerObjectController)VehicleFactory.createTank(screen, startLevel, 0f, true);
		
		((VehicleObject)screen.player.model).velocity = 20f;
		
	}
	
	public void update(float dt) {
		
		//if not existing, generate at least one segment below or above current segment, to enable player to progress
		int currentLevel = screen.player.getModel().level;
		if(currentLevel > 0 && !LevelSegment.exists(currentLevel-1, screen)) {
			generateLevelSegment(currentLevel-1, 20);
		}
		
		if(currentLevel < 19 && currentLevel > 0 && !LevelSegment.exists(currentLevel+1, screen)) {
			generateLevelSegment(currentLevel+1, 20);
		}
		
		//sometimes generate really long (recovery) segments below
		if(screen.random.nextFloat()>0.98) {
			int targetLevel = Math.min(currentLevel-3, 0);
			if(LevelSegment.exists(targetLevel, screen))
				generateLevelSegment(targetLevel, 50);
		}
		
		//randomly pick an empty level and start a segment
		if(screen.random.nextFloat()>0.97) {
			int targetLv = screen.random.nextInt(Constants.LEVEL_COUNT);
			if(!LevelSegment.exists(targetLv, screen)) {
				generateLevelSegment(targetLv, 30);
			}
		}
		
	}


	private void generateLevelSegment(int level, int avglength) {
		
		if(level < 0 || level >= Constants.LEVEL_COUNT) level = screen.random.nextInt(Constants.LEVEL_COUNT);
		
		//start off screen in front
		float start = screen.player.model.x + screen.random.nextInt(40)+Constants.ASCENT_LENGTH+Constants.SCREEN_WIDTH/20;
		int length = avglength * screen.random.nextInt(avglength/2);
		
		LevelSegment l = new LevelSegment(start, length, level);
		
		screen.levels[level] = l;
		
		//add some transitions.
		//a transition onto the level if we can
		if(level > 0 && LevelSegment.exists(level-1, screen) && screen.levels[level-1].isOnSegment(start)) {
			screen.levels[level-1].addLevelTransition(start, true);
		}
		
		//a downward transition onto the level if we can
		if(level > 0 && level < 19 && LevelSegment.exists(level+1, screen) && screen.levels[level+1].isOnSegment(start)) {
			screen.levels[level+1].addLevelTransition(start+Constants.ASCENT_LENGTH, false);
		}
		
		float len = l.end - l.start - 2*Constants.ASCENT_LENGTH;
		while(len > 2*Constants.ASCENT_LENGTH) {
			float offset = Constants.ASCENT_LENGTH+screen.random.nextInt((int)Constants.ASCENT_LENGTH);
			len -= offset;
			float pos = l.start + len;

			boolean up = screen.random.nextBoolean();

			l.addLevelTransition(pos, up);
		}
	}
	
}
