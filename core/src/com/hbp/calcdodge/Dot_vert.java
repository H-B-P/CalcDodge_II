package com.hbp.calcdodge;


public class Dot_vert extends Dot{
	   float m;
	   
	   Dot_vert(float xposn, float speed, float start_time){
		   super(xposn, start_time);
		   m=speed;
	   }
	   
	   @Override
	   
	   void locate_to_function(){
		   y=m*t;
		   x=k;
	   }
}