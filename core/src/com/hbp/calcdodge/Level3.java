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
			 secondlimit=180;
		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			if (seconds==1){
				   dots.add(new Dot_vert(0,0.5f,8));
			   }
			   else{
				   if((seconds%6==0 && seconds>6 && seconds<60)||(seconds>=60 && seconds<110 && seconds%4==0)){
					   int a=0;
					   int b=0;
					   while (a==b){
						   a=MathUtils.random(-1,1);
						   b=MathUtils.random(-1,1);
					   }
					   if (MathUtils.random(0,1)>0){
						   dots.add(new Dot_vert(a,0.8f, seconds+4));
					   }
					   else{
						   dots.add(new Dot_horz(a,0.8f, seconds+4));   
					   }
					   if (MathUtils.random(0,1)>0){
						   dots.add(new Dot_vert(b,-0.8f, seconds+4));
					   }
					   else{
						   dots.add(new Dot_horz(b,-0.8f, seconds+4));
					   }
					   System.out.println("make a thing!");
				   }
			   }
		}
		
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level2(game, true));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level3(game, true));
		}
}