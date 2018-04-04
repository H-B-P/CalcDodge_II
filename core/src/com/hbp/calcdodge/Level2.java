package com.hbp.calcdodge;

public class Level2 extends GameScreen{
	   
	   Level2(final CalcDodge gam, boolean play_the_sound){
		   super(gam, play_the_sound);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
			LEVEL="xdotdotydotdot";
			shields=2;
			 secondlimit=150;
		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			if (seconds<60 && seconds%10==2){
				   dots.add(new Dot_vert(Math.round(pod_x),plusorminus()*0.5f,seconds+8));
			   }
			if (seconds<60 && seconds%10==7){
				   dots.add(new Dot_horz(Math.round(pod_y),plusorminus()*0.5f,seconds+8));
			   }
			if (seconds==70){
				int z=plusorminus();
				   dots.add(new Dot_vert(-1,z*0.3f,seconds+10));
				   dots.add(new Dot_vert(0,z*0.3f,seconds+10));
				   dots.add(new Dot_vert(1,z*0.3f,seconds+10));
			}
			if (seconds>=80 && seconds%10==0 && seconds<135){
				int z=plusorminus();
				   dots.add(new Dot_horz(-1,z*0.5f,seconds+10));
				   dots.add(new Dot_horz(0,z*0.5f,seconds+10));
				   dots.add(new Dot_horz(1,z*0.5f,seconds+10));
			}
			if (seconds>=80 && seconds%10==5 && seconds<135){
				int z=plusorminus();
				   dots.add(new Dot_vert(-1,z*0.5f,seconds+10));
				   dots.add(new Dot_vert(0,z*0.5f,seconds+10));
				   dots.add(new Dot_vert(1,z*0.5f,seconds+10));
			}
		}
		
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level2(game, true));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level2(game, true));
		}
}