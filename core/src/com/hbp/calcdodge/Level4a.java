package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Level4a extends GameScreen{
	   
	   Level4a(final CalcDodge gam, boolean play_the_sound, boolean start_music){
		   super(gam, play_the_sound,start_music);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
		   
		   
			LEVEL="xdotydashdash";
			level_ident_s="level 4'";
			shields=2;
			 secondlimit=160;
			 
			pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L4a.png"));
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
		   if (seconds%20==0 && seconds>=60 && seconds<=120){
			   int z1=plusorminus();
			   int z2=plusorminus();
			   dots.add(new Dot_vert(-1,z1*0.25f,seconds+20));
			   dots.add(new Dot_vert(0,z1*0.25f,seconds+20));
			   dots.add(new Dot_vert(1,z1*0.25f,seconds+20));
			   dots.add(new Dot_horz(-1,z2*0.2f,seconds+20));
			   dots.add(new Dot_horz(0,z2*0.2f,seconds+20));
			   dots.add(new Dot_horz(1,z2*0.2f,seconds+20));
		   }
		}
		
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level5a(game, true, false));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level4a(game, true, false));
		}
}