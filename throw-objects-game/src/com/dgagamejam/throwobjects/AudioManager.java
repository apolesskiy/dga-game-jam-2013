package com.dgagamejam.throwobjects;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class AudioManager {
	//public final String SOUNDFILENAME = "";
	public void play(String soundfilename){
		try{
		FileHandle soundFile = Gdx.files.internal( soundfilename );
	    Music soundToPlay = Gdx.audio.newMusic( soundFile );
	    soundToPlay.setLooping( true );

	    soundToPlay.play();
		}
		catch(Exception e){
			
		}
	}	
}

