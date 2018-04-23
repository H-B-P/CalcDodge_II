package com.hbp.calcdodge;

public class Dot_horz extends Dot{
	   float m;
	   
	   Dot_horz(float yposn, float speed, int start_time){
		   super(yposn, start_time);
		   m=speed;
	   }
	   
	   @Override
	   
	   void locate_to_function(){
		   y=k;
		   x=m*t;
	   }
	   @Override
	   String return_x_line(){
		   return "x = " + double_formatted(m) + "t = "+double_formatted(x);
	   }
	   @Override
	   String return_y_line(){
		   return "y = " + double_formatted(k);
	   }
}