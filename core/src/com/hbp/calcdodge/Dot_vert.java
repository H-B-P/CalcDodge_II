package com.hbp.calcdodge;


public class Dot_vert extends Dot{
	   float m;
	   
	   Dot_vert(float xposn, float speed, int start_time){
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
		   return "x = " + double_formatted(k);
	   }
	   @Override
	   String return_y_line(){
		   return "y = " + double_formatted(m) + "t = "+double_formatted(y);
	   }
}