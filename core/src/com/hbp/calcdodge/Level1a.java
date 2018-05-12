package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Level1a extends GameScreen{
	   
	   Level1a(final CalcDodge gam, boolean play_the_sound, boolean start_music){
		   super(gam, play_the_sound, start_music);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
		   
			LEVEL="2xdotydot";
			level_ident_s="level 1'";
			shields=2;
			 secondlimit=120;
			 
			pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L1b.png"));
		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			if (seconds<=105){
			    if (seconds%8==1){
					dots.add(new Dot_horz(MathUtils.random(-1,1),0.5f,seconds+8));
					
				}
				if (seconds%8==3){
					dots.add(new Dot_horz(MathUtils.random(-1,1),-0.5f,seconds+8));	
				}
				if (seconds%8==5){
					dots.add(new Dot_horz(Math.round(pod_y),0.5f,seconds+8));
					
				}
				if (seconds%8==7){
					dots.add(new Dot_horz(Math.round(pod_y),-0.5f,seconds+8));	
				}
			}
//			else{
//				if (seconds==120){
//					dots.add(new Dot_horz(1,1f,seconds+5));
//					dots.add(new Dot_horz(1,-1f,seconds+5));
//					dots.add(new Dot_horz(0,1f,seconds+6));
//					dots.add(new Dot_horz(0,-1f,seconds+6));
//					dots.add(new Dot_horz(-1,1f,seconds+7));
//					dots.add(new Dot_horz(-1,-1f,seconds+7));
//				}
//				if (seconds==130){
//					dots.add(new Dot_horz(-1,1f,seconds+5));
//					dots.add(new Dot_horz(-1,-1f,seconds+5));
//					dots.add(new Dot_horz(0,1f,seconds+6));
//					dots.add(new Dot_horz(0,-1f,seconds+6));
//					dots.add(new Dot_horz(1,1f,seconds+7));
//					dots.add(new Dot_horz(1,-1f,seconds+7));
//				}
//			}
		}
		
	   
	   void horizontal_dagger(int locn, float spear_speed, int first_time){
		   dots.add(new Dot_horz(locn, spear_speed, first_time));
		   dots.add(new Dot_horz(locn, spear_speed, first_time+2));
	   }
	   
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level2a(game, true, false));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level1a(game, true, false));
		}
}