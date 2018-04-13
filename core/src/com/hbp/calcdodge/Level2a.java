package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Level2a extends GameScreen{
	   
	   Level2a(final CalcDodge gam, boolean play_the_sound){
		   super(gam, play_the_sound);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
			LEVEL="xdotdotydash";
			shields=2;
			 secondlimit=150;
			 
			pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L2a.png"));
		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			
		}
		
	   
	   void horizontal_dagger(int locn, float spear_speed, int first_time){
		   dots.add(new Dot_horz(locn, spear_speed, first_time));
		   dots.add(new Dot_horz(locn, spear_speed, first_time+2));
	   }
	   
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level2a(game, true));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level2a(game, true));
		}
}