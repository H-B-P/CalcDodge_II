package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Level1 extends GameScreen{
	
	   Level1(final CalcDodge gam, boolean play_the_sound,boolean start_music){
		   super(gam, play_the_sound, start_music);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
		   
		   
		   
			LEVEL="xdotydot";
			level_ident_s="level 1";
			shields=2;
			 secondlimit=80;
			 
			pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L1.png"));
		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			if (seconds==1){
				   dots.add(new Dot_vert(0,0.5f,8));
			   }
			   else{
				   if((seconds%6==0 && seconds>6 && seconds<36)||(seconds>=36 && seconds<72 && seconds%4==0)){
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
				   }
			   }
		}
	   
	   @Override
	   
		void level_specific_success(){
		   if (prefs.getInteger("LevelsBeat")>0){
			   bgm.stop();
			   bgm.dispose();
			   game.setScreen(new SelectScreen(game, true));
		   }
		   else{
			   bgm.stop();
			   bgm.dispose();
			   prefs.putInteger("LevelsBeat",1);
			   prefs.flush();
			   game.setScreen(new Level2(game, true, true));
		   }
			
		}
		
	   @Override
	   
		void level_specific_failure(){
		   if (prefs.getInteger("LevelsBeat")>0){
			   bgm.stop();
			   bgm.dispose();
			   game.setScreen(new SelectScreen(game, true));
		   }
		   else{
			   bgm.stop();
			   bgm.dispose();
			   game.setScreen(new Level1(game, true, true));
		   }
			
		}
}