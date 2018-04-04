
package com.hbp.calcdodge;

/*
 * ~SUMMARY~
 * 
 * This is the actual game.
 * It's pretty empty: all the fun happens in the screens, which we hand off to as soon as possible.
 * 
 */

import com.badlogic.gdx.Game;


public class CalcDodge extends Game {
	
	public void create() {
		
		this.setScreen(new Level2(this, false)); //Hand off to game screen.
		
	}

	public void render() {
		super.render(); // I deleted this once and I deeply regretted it.
	}

	public void dispose() {
	}

}
