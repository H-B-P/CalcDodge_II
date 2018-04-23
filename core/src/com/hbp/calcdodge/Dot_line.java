package com.hbp.calcdodge;

public class Dot_line extends Dot{
	float k;
	float m;
	float c;
	   
	   Dot_line(float speed, float M, float C, int start_time){
		   super(C, start_time);
		   k=speed;
		   m=M;
		   c=C;
	   }
	   
	   @Override
	   
	   void locate_to_function(){
		   x=k*t;
		   y=m*x+c;
	   }
	   
	   @Override
	   String return_x_line(){
		   return "x = " + double_formatted(k) + "t = "+double_formatted(x);
	   }
	   @Override
	   String return_y_line(){
		   if (c<0){
			   return "y = " + double_formatted(m) + "x - " + double_formatted(-c);
		   }
		   else{
			   return "y = " + double_formatted(m) + "x + " + double_formatted(c);
		   }
	   }
}