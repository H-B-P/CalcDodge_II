package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Level4 extends GameScreen{
	   
	   Level4(final CalcDodge gam, boolean play_the_sound){
		   super(gam, play_the_sound);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
			LEVEL="xy";
			shields=2;
			 secondlimit=180;
			 
			 pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L4.png"));

		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			
				   if (seconds<80 && seconds%6==2){
					   dots.add(new Dot_vert(Math.round(pod_x),plusorminus()*1.5f,seconds+3));
				   }
				   if (seconds<80 && seconds%12==5){
					   dots.add(new Dot_vert(MathUtils.random(-1,1),1.5f,seconds+3));
					   dots.add(new Dot_vert(MathUtils.random(-1,1),-1.5f,seconds+3));
				   }
				   if (seconds<80 && seconds%12==11){
					   dots.add(new Dot_horz(MathUtils.random(-1,1),1.0f,seconds+3));
					   dots.add(new Dot_horz(MathUtils.random(-1,1),-1.0f,seconds+3));
				   }
				   
				   if (seconds>=90 && seconds%10==0 && seconds<170){
					   dots.add(new Dot_vert_quad(Math.round(pod_x), plusorminus()*0.4f,0,0, seconds+5));
				   }
				   if (seconds>=90 && seconds%10==5 && seconds<170){
					   dots.add(new Dot_horz_quad(MathUtils.random(-1,1), plusorminus()*0.2f,0,0, seconds+5));
					   dots.add(new Dot_vert_quad(MathUtils.random(-1,1), plusorminus()*0.2f,0,0, seconds+5));
				   }
		}
		
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level1(game, true));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level4(game, true));
		}
	   
}