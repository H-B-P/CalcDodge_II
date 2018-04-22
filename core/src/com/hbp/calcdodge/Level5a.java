package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;



public class Level5a extends GameScreen{
	   
	int q_vert=1;
	int q_horz=1;
	
	   Level5a(final CalcDodge gam, boolean play_the_sound){
		   super(gam, play_the_sound);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
			LEVEL="xdotdoty";
			shields=2;
			 secondlimit=300;
			 
			 pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L6.png"));

		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			
		   if (seconds==1){
			   dots.add(new Dot_vert(0,0.5f,8));
		   }
		   if(seconds%6==0 && seconds>0 && seconds<60){
			   int a=0;
			   int b=0;
			   while (a==b){
				   a=MathUtils.random(-1,1);
				   b=MathUtils.random(-1,1);
			   }
			   if (MathUtils.random(0,1)>0){
				   dots.add(new Dot_vert(a,0.5f, seconds+10));
			   }
			   else{
				   dots.add(new Dot_horz(a,0.5f, seconds+10));   
			   }
			   if (MathUtils.random(0,1)>0){
				   dots.add(new Dot_vert(b,-0.5f, seconds+10));
			   }
			   else{
				   dots.add(new Dot_horz(b,-0.5f, seconds+10));
			   }
		   }
		   if(seconds%10==0 && seconds>60 && seconds<120){
			   int a=plusorminus();
			   dots.add(new Dot_horz(1,a*0.4f, seconds+10));
			   dots.add(new Dot_horz(0,a*0.4f, seconds+10));
			   dots.add(new Dot_horz(-1,a*0.4f, seconds+10));
			   
			   dots.add(new Dot_vert(plusorminus(),plusorminus()*0.4f, seconds+10));
		   }
		   
		   if(seconds>120 && seconds<190){
			   if (seconds%16==0){
				   int a=plusorminus();
				   dots.add(new Dot_horz_quad(1,-0.06f,1, seconds+10));
				   dots.add(new Dot_horz_quad(0,-0.06f,1, seconds+10));
				   dots.add(new Dot_horz_quad(-1,-0.06f,1, seconds+10));
			   }
			   if (seconds%16==8){
				   int a=plusorminus();
				   dots.add(new Dot_horz_quad(plusorminus(),-0.06f,1, seconds+10));
				   dots.add(new Dot_horz_quad(0, -0.06f,1, seconds+10));
				}
		   }
		   if(seconds>200 && seconds<280){
			   if (seconds%8==0){
				   int k =MathUtils.random(-1,1);
				   float con=0.7f;
				   
				   if (k==1){
					   dots.add(new Dot_horz(-1,plusorminus()*con,seconds+10));
					   dots.add(new Dot_horz(0,plusorminus()*con,seconds+10));
					   dots.add(new Dot_horz(1,con,seconds+10));
					   dots.add(new Dot_horz(1,-con,seconds+10));
				   }
				   if (k==0){
					   dots.add(new Dot_horz(-1,plusorminus()*con,seconds+10));
					   dots.add(new Dot_horz(0,con,seconds+10));
					   dots.add(new Dot_horz(0,-con,seconds+10));
					   dots.add(new Dot_horz(1,plusorminus()*con,seconds+10));
				   }
				   if (k==-1){
					   dots.add(new Dot_horz(-1,con,seconds+10));
					   dots.add(new Dot_horz(-1,-con,seconds+10));
					   dots.add(new Dot_horz(0,plusorminus()*con,seconds+10));
					   dots.add(new Dot_horz(1,plusorminus()*con,seconds+10));
				   }
			   }
		   }
		   
		}
	   
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level5a(game, true));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level5a(game, true));
		}
	   
}