package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;



public class Level5 extends GameScreen{
	   
	int q_vert=1;
	int q_horz=1;
	
	
	
	   Level5(final CalcDodge gam, boolean play_the_sound, boolean start_music){
		   super(gam, play_the_sound, start_music);
		   CONGRATULATE=true;
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
		   
			LEVEL="xydot";
			level_ident_s="level 5";
			shields=2;
			 secondlimit=260;
			 
			 pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L5.png"));

		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			
		   if (seconds==1){
			   dots.add(new Dot_vert(-1,plusorminus()*0.6f,seconds+6));
			   dots.add(new Dot_vert(0,0.6f,seconds+6));
			   dots.add(new Dot_vert(0,-0.6f,seconds+6));
			   dots.add(new Dot_vert(1,plusorminus()*0.6f,seconds+6));
		   }
				   if (seconds<50 && seconds>10 && seconds%4==0){
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
				   
				   if (seconds>=60 && seconds<110){
					   if (seconds%5==0){
						   int k_one=plusorminus();
						   int k_two=plusorminus();
						   dots.add(new Dot_vert_quad(0,k_one*0.15f,-k_one, seconds+5));
						   dots.add(new Dot_vert_quad(plusorminus(),k_two*0.15f,-k_two, seconds+5));
					   }
				   }
				   
				   if (seconds<160 && seconds>=120 && seconds%4==0){
					   int k =MathUtils.random(-1,1);
					   
					   if (k==1){
						   dots.add(new Dot_vert(1,plusorminus(),seconds+6));
						   dots.add(new Dot_vert(0, 1,seconds+6));
						   dots.add(new Dot_vert(0, -1,seconds+6));
						   dots.add(new Dot_vert(-1,1,seconds+6));
						   dots.add(new Dot_vert(-1,-1,seconds+6));
					   }
					   if (k==0){
						   dots.add(new Dot_vert(-1,1,seconds+6));
						   dots.add(new Dot_vert(-1,-1,seconds+6));
						   dots.add(new Dot_vert(0,plusorminus(),seconds+6));
						   dots.add(new Dot_vert(1,1,seconds+6));
						   dots.add(new Dot_vert(1,-1,seconds+6));
					   }
					   if (k==-1){
						   dots.add(new Dot_vert(-1,plusorminus(),seconds+6));
						   dots.add(new Dot_vert(0, 1,seconds+6));
						   dots.add(new Dot_vert(0, -1,seconds+6));
						   dots.add(new Dot_vert(1,1,seconds+6));
						   dots.add(new Dot_vert(1,-1,seconds+6));
					   }
				   }
				   
				   if (seconds>=170 && seconds<230){
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
		   prefs.putInteger("LevelsBeat",5);
		   prefs.flush();
		   bgm.stop();
		   bgm.dispose();
		   game.setScreen(new SelectScreen(game, true));
		}
		
	   @Override
	   
	   void level_specific_failure(){
		   if (prefs.getInteger("LevelsBeat")>4){
			   bgm.stop();
			   bgm.dispose();
			   game.setScreen(new SelectScreen(game, true));
		   }
		   else{
			   bgm.stop();
			   bgm.dispose();
			   game.setScreen(new Level5(game, true, true));
		   }
			
		}
	   
}