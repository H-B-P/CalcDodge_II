
package com.hbp.calcdodge;

/*
 * ~SUMMARY~
 * 
 * This is the actual game.
 * It's pretty empty: all the fun happens in the screens, which we hand off to as soon as possible.
 * 
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class CalcDodge extends Game {
	
	public void create() {
		Preferences prefsII = Gdx.app.getPreferences("dotdash");
		
		if (prefsII.getInteger("LevelsBeat")>1){
			this.setScreen(new SelectScreen(this, true)); //Hand off to game screen.
		}
		else{
			this.setScreen(new Level1(this, true, true)); //Hand off to game screen.
		}
		
		
	}

	public void render() {
		super.render(); // I deleted this once and I deeply regretted it.
	}

	public void dispose() {
	}

}
