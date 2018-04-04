package com.hbp.calcdodge;

import com.badlogic.gdx.math.MathUtils;

public class Level3 extends GameScreen{
	   
	   Level3(final CalcDodge gam, boolean play_the_sound){
		   super(gam, play_the_sound);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
			LEVEL="xdotydotdot";
			shields=2;
			 secondlimit=160;
		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			
				   if (seconds<80 && seconds%10==2){
					   int z=plusorminus();
					   dots.add(new Dot_vert(Math.round(pod_x),z*0.4f,seconds+8));
					   dots.add(new Dot_vert(Math.round(pod_x),z*0.8f,seconds+8));
				   }
				   if (seconds<80 && seconds%10==7){
					   int z=plusorminus();
					   dots.add(new Dot_horz(Math.round(pod_y),z*0.3f,seconds+8));
					   dots.add(new Dot_horz(Math.round(pod_y),z*0.6f,seconds+8));
				   }
				   
				   if (seconds>=90 && seconds%10==0 && seconds<150){
					   int z_one=plusorminus();
					   dots.add(new Dot_horz(-1,z_one*0.5f,seconds+10));
					   dots.add(new Dot_horz(0,z_one*0.5f,seconds+10));
					   dots.add(new Dot_horz(1,z_one*0.5f,seconds+10));
					   int z_two=plusorminus();
					   dots.add(new Dot_vert(-1,z_two*0.5f,seconds+10));
					   dots.add(new Dot_vert(0,z_two*0.5f,seconds+10));
					   dots.add(new Dot_vert(1,z_two*0.5f,seconds+10));
				   }
		}
		
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level1(game, true));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level3(game, true));
		}
	   
}