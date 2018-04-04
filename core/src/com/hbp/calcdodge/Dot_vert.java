package com.hbp.calcdodge;

import com.badlogic.gdx.math.Rectangle;

public class Dot_vert extends Dot{
	   float m;
	   
	   Dot_vert(float xposn, float speed, float start_time){
		   super(xposn, start_time);
		   m=speed;
	   }
	   
	   @Override
	   
	   void locate_to_function(){
		   y=m*t;
		   x=c;
	   }
}