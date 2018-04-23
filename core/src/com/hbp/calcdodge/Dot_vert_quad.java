package com.hbp.calcdodge;

public class Dot_vert_quad extends Dot{
	   float a;
	   float b;
	   float c;
	   
	   Dot_vert_quad(float xposn, float A, float B, float C, int start_time){
		   super(xposn, start_time);
		   a=A;
		   b=B;
		   c=C;
	   }
	   
	   Dot_vert_quad(float xposn, float A, float C, int start_time){
		   this(xposn, A, 0, C, start_time);
	   }
	   
	   Dot_vert_quad(float xposn, float A, int start_time){
		   this(xposn, A, 0, 0, start_time);
	   }
	   @Override
	   
	   void locate_to_function(){
		   y=a*t*t+b*t+c;
		   x=k;
	   }
	   
	   @Override
	   String return_y_line(){
		   if (b!=0){
			   return "y = " + double_formatted(a) + "t^2 + "+ double_formatted(b) + "t + " + double_formatted(c);
		   }
		   if (c==0){
			   return "y = " + double_formatted(a) + "t^2 = "+ double_formatted(y);
		   }
		   return "y = " + double_formatted(a) + "t^2 + "+double_formatted(c);
	   }
	   @Override
	   String return_x_line(){
		   return "x = " + double_formatted(k);
	   }
}