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
	   @Override
	   String return_x_line(){
		   return "x = " + k;
	   }
	   @Override
	   String return_y_line(){
		   return "y = " + m + "t = "+double_formatted(y);
	   }
}