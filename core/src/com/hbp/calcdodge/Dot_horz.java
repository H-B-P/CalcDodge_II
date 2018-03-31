package com.hbp.calcdodge;

import com.badlogic.gdx.math.Rectangle;

public class Dot_horz extends Dot{
	   float m;
	   
	   Dot_horz(float xposn, float speed, float start_time){
		   super(xposn, start_time);
		   m=speed;
	   }
	   
	   @Override
	   
	   void locate_to_function(){
		   y=c;
		   x=m*(t-t0);
	   }
}