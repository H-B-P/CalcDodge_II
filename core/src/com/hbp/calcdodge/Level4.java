package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;



public class Level4 extends GameScreen{
	   
	int q_vert=1;
	int q_horz=1;
	
	   Level4(final CalcDodge gam, boolean play_the_sound, boolean start_music){
		   super(gam, play_the_sound, start_music);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
		   
			LEVEL="xy";
			level_ident_s="level 4";
			shields=2;
			 secondlimit=200;
			 
			 pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L4.png"));

		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			
				   if (seconds<60 && seconds%6==2){
					   dots.add(new Dot_vert(Math.round(pod_x),plusorminus()*1.5f,seconds+3));
				   }
				   if (seconds<60 && seconds%12==5){
					   dots.add(new Dot_vert(MathUtils.random(-1,1),1.5f,seconds+3));
					   dots.add(new Dot_vert(MathUtils.random(-1,1),-1.5f,seconds+3));
				   }
				   if (seconds<60 && seconds%12==11){
					   dots.add(new Dot_horz(MathUtils.random(-1,1),1.0f,seconds+3));
					   dots.add(new Dot_horz(MathUtils.random(-1,1),-1.0f,seconds+3));
				   }
				   
				   if (seconds>=65 && seconds%10==5 && seconds<130){
					   dots.add(new Dot_vert_quad(Math.round(pod_x), plusorminus()*0.4f, seconds+5));
				   }
				   if (seconds>=65 && seconds%10==0 && seconds<130){
					   dots.add(new Dot_horz_quad(MathUtils.random(-1,1), plusorminus()*0.2f,0,0, seconds+5));
					   dots.add(new Dot_vert_quad(MathUtils.random(-1,1), plusorminus()*0.2f,0,0, seconds+5));
				   }
				   
				   if (seconds>=140 && seconds<190){
		   			
					   if (seconds%8==0){
						   q_vert=plusorminus();
						   vertical_spear(0, -q_horz*1.2f, seconds+5);
						   vertical_spear(q_vert, -q_horz*1.2f, seconds+5);   
					   }
					   if (seconds%8==4){
						   q_horz=plusorminus();
						   horizontal_spear(0, -q_vert*1.2f, seconds+5);
						   horizontal_spear(q_horz, -q_vert*1.2f, seconds+5);
					   }
					   
				   }
		}
		
	   void vertical_spear(int locn, float spear_speed, int first_time){
		   dots.add(new Dot_vert(locn, spear_speed, first_time));
		   dots.add(new Dot_vert(locn, spear_speed, first_time+1));
		   dots.add(new Dot_vert(locn, spear_speed, first_time+2));
		   dots.add(new Dot_vert(locn, spear_speed, first_time+3));
		   dots.add(new Dot_vert(locn, spear_speed, first_time+4));

	   }
	   
	   void horizontal_spear(int locn, float spear_speed, int first_time){
		   dots.add(new Dot_horz(locn, spear_speed, first_time));
		   dots.add(new Dot_horz(locn, spear_speed, first_time+1));
		   dots.add(new Dot_horz(locn, spear_speed, first_time+2));
		   dots.add(new Dot_horz(locn, spear_speed, first_time+3));
		   dots.add(new Dot_horz(locn, spear_speed, first_time+4));
	   }
	   
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level5(game, true, false));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level4(game, true, false));
		}
	   
}