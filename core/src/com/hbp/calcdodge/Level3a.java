package com.hbp.calcdodge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Level3a extends GameScreen{
	   
	   Level3a(final CalcDodge gam, boolean play_the_sound){
		   super(gam, play_the_sound);
	   }
	   
	   @Override
	   
	   void level_specific_setup(){
			LEVEL="xdoty";
			shields=2;
			 secondlimit=180;
			 
			pod_t= new Texture(Gdx.files.internal("cartesian_dodger_L3a.png"));
		}
	   
	   @Override
	   
	   void level_specific_attack_pattern(){
			if (seconds==5){
				dots.add(new Dot_line(0.5f, 1, 1, seconds+5));
				dots.add(new Dot_line(0.5f, 1, 0, seconds+5));
				dots.add(new Dot_line(0.5f, 1, -1, seconds+5));
			}
			if (seconds==15){
				dots.add(new Dot_line(0.5f, 1, -1, seconds+5));
				dots.add(new Dot_line(0.5f, 0, -1, seconds+5));
				dots.add(new Dot_line(0.5f, -1, -1, seconds+5));
			}
			if (seconds==25){
				dots.add(new Dot_line(0.5f, 1, -1, seconds+5));
				dots.add(new Dot_line(-0.5f, 1, -1, seconds+5));
			}
			if (seconds>=35 && seconds<=165){
				if (seconds%15==5){
					intercept_varying();
				}
				if (seconds%15==10){
					gradient_varying();
				}
				if (seconds%15==0){
					chrono_varying();
				}
			}
		}
	   
	   void gradient_varying(){
		   int center_grad=MathUtils.random(-1,1);
		   int center_cept=MathUtils.random(-1,1);
		   int velo=plusorminus();
		   dots.add(new Dot_line(velo*0.8f, center_grad+1, center_cept, seconds+5));
		dots.add(new Dot_line(velo*0.8f, center_grad, center_cept, seconds+5));
		dots.add(new Dot_line(velo*0.8f, center_grad-1, center_cept, seconds+5));
	   }
	   
	   void intercept_varying(){
		   int center_grad=plusorminus();
		   int center_cept=MathUtils.random(-1,1);
		   int velo=plusorminus();
		   dots.add(new Dot_line(velo*0.8f, center_grad, center_cept+1, seconds+5));
			dots.add(new Dot_line(velo*0.8f, center_grad, center_cept, seconds+5));
			dots.add(new Dot_line(velo*0.8f, center_grad, center_cept-1, seconds+5));
	   }
	   
	   void chrono_varying(){
		   int center_grad=MathUtils.random(-1,1);
		   int center_cept=MathUtils.random(-1,1);
		   dots.add(new Dot_line(0.8f, center_grad, center_cept, seconds+5));
		dots.add(new Dot_line(-0.8f, center_grad, center_cept, seconds+5));
	   }
	   
	   @Override
	   
		void level_specific_success(){
			game.setScreen(new Level3a(game, true));
		}
		
	   @Override
	   
		void level_specific_failure(){
			game.setScreen(new Level3a(game, true));
		}
}