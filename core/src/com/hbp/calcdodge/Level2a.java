package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Level2a extends GameScreen{
	   
	   Level2a(final CalcDodge gam, boolean play_the_sound,boolean start_music){
		   super(gam, play_the_sound, start_music);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
		   level_ident_s="level 2'";
			LEVEL="xdotdotydash";
			shields=2;
			 secondlimit=120;
			 
			pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L2a.png"));
		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
		   
		   if (seconds==10){
				int z=1;
				   dots.add(new Dot_horz(-1,z*0.4f,seconds+10));
				   dots.add(new Dot_horz(0,z*0.4f,seconds+10));
				   dots.add(new Dot_horz(1,z*0.4f,seconds+10));
			   }
		   if (seconds==20){
				int z=1;
				   dots.add(new Dot_vert(-1,z*0.5f,seconds+10));
				   dots.add(new Dot_vert(0,z*0.5f,seconds+10));
				   dots.add(new Dot_vert(1,z*0.5f,seconds+10));
			   }
		   if (seconds==30){
				int z=-1;
				   dots.add(new Dot_horz(-1,z*0.4f,seconds+10));
				   dots.add(new Dot_horz(0,z*0.4f,seconds+10));
				   dots.add(new Dot_horz(1,z*0.4f,seconds+10));
			   }
		   if (seconds==40){
				int z=-1;
				   dots.add(new Dot_vert(-1,z*0.5f,seconds+10));
				   dots.add(new Dot_vert(0,z*0.5f,seconds+10));
				   dots.add(new Dot_vert(1,z*0.5f,seconds+10));
			   }
		   if ((seconds+15)%15==0 && seconds>=55 && seconds<=100){
			   int z1=plusorminus();
			   int z2=plusorminus();
			   dots.add(new Dot_vert(-1,z1*0.5f,seconds+10));
			   dots.add(new Dot_vert(0,z1*0.5f,seconds+10));
			   dots.add(new Dot_vert(1,z1*0.5f,seconds+10));
			   dots.add(new Dot_horz(-1,z2*0.4f,seconds+10));
			   dots.add(new Dot_horz(0,z2*0.4f,seconds+10));
			   dots.add(new Dot_horz(1,z2*0.4f,seconds+10));
		   }

		}
		
	   
	   void horizontal_dagger(int locn, float spear_speed, int first_time){
		   dots.add(new Dot_horz(locn, spear_speed, first_time));
		   dots.add(new Dot_horz(locn, spear_speed, first_time+2));
	   }
	   
	   @Override
	   
		void level_specific_success(){
		   bgm.stop();
		   bgm.dispose();
		   game.setScreen(new SelectScreen(game, true));
		}
		
	   @Override
	   
		void level_specific_failure(){
		   bgm.stop();
		   bgm.dispose();
		   game.setScreen(new SelectScreen(game, true));
		}
}