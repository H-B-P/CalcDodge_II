package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Level2 extends GameScreen{
	   
	   Level2(final CalcDodge gam, boolean play_the_sound, boolean start_music){
		   super(gam, play_the_sound, start_music);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
		   
			LEVEL="xdotdotydotdot";
			level_ident_s="level 2";
			shields=2;
			 secondlimit=120;
				pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L2.png"));

		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			if (seconds<40 && seconds%10==2){
				   dots.add(new Dot_vert(Math.round(pod_x),plusorminus()*0.5f,seconds+8));
			   }
			if (seconds<40 && seconds%10==7){
				   dots.add(new Dot_horz(Math.round(pod_y),plusorminus()*0.5f,seconds+8));
			   }
			if (seconds==50){
				int z=plusorminus();
				   dots.add(new Dot_vert(-1,z*0.3f,seconds+10));
				   dots.add(new Dot_vert(0,z*0.3f,seconds+10));
				   dots.add(new Dot_vert(1,z*0.3f,seconds+10));
			}
			if (seconds>=60 && seconds%10==0 && seconds<105){
				int z=plusorminus();
				   dots.add(new Dot_horz(-1,z*0.5f,seconds+10));
				   dots.add(new Dot_horz(0,z*0.5f,seconds+10));
				   dots.add(new Dot_horz(1,z*0.5f,seconds+10));
			}
			if (seconds>=60 && seconds%10==5 && seconds<105){
				int z=plusorminus();
				   dots.add(new Dot_vert(-1,z*0.5f,seconds+10));
				   dots.add(new Dot_vert(0,z*0.5f,seconds+10));
				   dots.add(new Dot_vert(1,z*0.5f,seconds+10));
			}
		}
		
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level3(game, true, false));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level2(game, true, false));
		}
}