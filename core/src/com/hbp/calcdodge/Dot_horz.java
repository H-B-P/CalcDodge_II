package com.hbp.calcdodge;

public class Dot_horz extends Dot{
	   float m;
	   
	   Dot_horz(float yposn, float speed, float start_time){
		   super(yposn, start_time);
		   m=speed;
	   }
	   
	   @Override
	   
	   void locate_to_function(){
		   y=c;
		   x=m*t;
	   }
}