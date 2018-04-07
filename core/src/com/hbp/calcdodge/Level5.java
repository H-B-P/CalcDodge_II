package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;



public class Level5 extends GameScreen{
	   
	int q_vert=1;
	int q_horz=1;
	
	   Level5(final CalcDodge gam, boolean play_the_sound){
		   super(gam, play_the_sound);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
			LEVEL="xydot";
			shields=1;
			 secondlimit=110;
			 
			 pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L5.png"));

		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			
				   if (seconds<40 && seconds%4==0){
					   int k =MathUtils.random(-1,1);
					   
					   if (k==1){
						   dots.add(new Dot_vert(-1,plusorminus(),seconds+6));
						   dots.add(new Dot_vert(0,plusorminus(),seconds+6));
						   dots.add(new Dot_vert(1,1,seconds+6));
						   dots.add(new Dot_vert(1,-1,seconds+6));
					   }
					   if (k==0){
						   dots.add(new Dot_vert(-1,plusorminus(),seconds+6));
						   dots.add(new Dot_vert(0,1,seconds+6));
						   dots.add(new Dot_vert(0,-1,seconds+6));
						   dots.add(new Dot_vert(1,plusorminus(),seconds+6));
					   }
					   if (k==-1){
						   dots.add(new Dot_vert(-1,1,seconds+6));
						   dots.add(new Dot_vert(-1,-1,seconds+6));
						   dots.add(new Dot_vert(0,plusorminus(),seconds+6));
						   dots.add(new Dot_vert(1,plusorminus(),seconds+6));
					   }
				   }
				   
				   if (seconds>=50 && seconds<100){
					   if (seconds%5==0){
						   int k_one=plusorminus();
						   int k_two=plusorminus();
						   dots.add(new Dot_vert_quad(0,k_one*0.15f,-k_one, seconds+5));
						   dots.add(new Dot_vert_quad(plusorminus(),k_two*0.15f,-k_two, seconds+5));
					   }
				   }
				   
				   
		}
	   
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level1(game, true));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level5(game, true));
		}
	   
}